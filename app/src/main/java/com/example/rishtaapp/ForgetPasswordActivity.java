package com.example.rishtaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForgetPasswordActivity extends AppCompatActivity {
    DBHelper dbHelper;
    EditText editUsername;
    TextView textPassword;
    Button buttonSubmit;
    TextView loginLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DBHelper(this);
        editUsername = findViewById(R.id.editusername);
        textPassword = findViewById(R.id.textpassword);
        buttonSubmit = findViewById(R.id.buttonsub);

        dbHelper = new DBHelper(this);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPassword.setText("");

                String username = editUsername.getText().toString().trim();


                String password = dbHelper.getPasswordByUsername(username);

                if (password != null) {
                    Toast.makeText(ForgetPasswordActivity.this, "Password Retrieved Successfully!", Toast.LENGTH_SHORT).show();

                    textPassword.setText( password);
                } else {

                    Toast.makeText(ForgetPasswordActivity.this, "Username not found", Toast.LENGTH_SHORT).show();
                }
            }
        });


        loginLink = findViewById(R.id.login_link);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}