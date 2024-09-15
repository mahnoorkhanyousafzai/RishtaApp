package com.example.rishtaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity {

    DBHelper db;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = new DBHelper(this);
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            // User is already logged in, go to Dashboard
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            intent.putExtra("username", sharedPreferences.getString("username", ""));
            startActivity(intent);
            finish();
        }
        EditText textUser = (EditText) findViewById(R.id.editusername);
        EditText textPass = (EditText) findViewById(R.id.editpassword);
        Button Loginbtn = (Button) findViewById(R.id.buttonlogin);
        TextView forget = findViewById(R.id.login_link);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });



        Loginbtn.setOnClickListener(v -> {
            String username = textUser.getText().toString();
            String password = textPass.getText().toString();

            // Validate inputs
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Check if user exists
                boolean isValid = db.checkUser(username, password);
                if (isValid) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.putString("username", username);
                    editor.apply();
                    // Go to Dashboard Activity
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}