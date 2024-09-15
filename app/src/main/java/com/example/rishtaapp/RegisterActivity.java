package com.example.rishtaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = new DBHelper(this);
        EditText textUser = (EditText) findViewById(R.id.editusername);
        EditText textPass = (EditText) findViewById(R.id.editpassword);
        Button Registerbtn = (Button) findViewById(R.id.buttonregister);


        Registerbtn.setOnClickListener(v -> {
            String username = textUser.getText().toString();
            String password = textPass.getText().toString();

            // Validate input
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(RegisterActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else if (!isPasswordValid(password)) {
                Toast.makeText(RegisterActivity.this, "Password must be at least 8 characters long and contain a number", Toast.LENGTH_SHORT).show();
            } else if (db.checkUsernameExists(username)) {
                Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
            } else {
                // Insert data into the database
                boolean isInserted = db.insertData(username, password);
                if (isInserted) {
                    Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    // Go to Dashboard Activity
                    Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Password validation
    private boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    }
