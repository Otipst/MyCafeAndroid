package com.project.mycafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInAdminActivity extends AppCompatActivity {

    EditText username, password;
    Button loginAdminButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_admin);

        username = (EditText) findViewById(R.id.usernameAdmin);
        password = (EditText) findViewById(R.id.passwordAdmin);
        loginAdminButton = (Button) findViewById(R.id.loginAdminButton);

        loginAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(username.getText().toString().equals("Admin")  && password.getText().toString().equals("Admin")) {
                    Intent intent = new Intent(SignInAdminActivity.this, FoodList.class);
                    startActivity(intent);
                } else if(username.getText().toString().equals("") && password.getText().toString().equals("")) {
                    Toast.makeText(SignInAdminActivity.this, "All field required", Toast.LENGTH_SHORT).show();
                } else if(username.getText().toString().equals("")) {
                    Toast.makeText(SignInAdminActivity.this, "Fill username field", Toast.LENGTH_SHORT).show();
                } else if(password.getText().toString().equals("")) {
                    Toast.makeText(SignInAdminActivity.this, "Fill password field", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignInAdminActivity.this, "Account Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}