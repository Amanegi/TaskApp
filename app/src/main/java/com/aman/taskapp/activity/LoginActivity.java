package com.aman.taskapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aman.taskapp.R;

public class LoginActivity extends AppCompatActivity {

    public static final String PREF_FILENAME = "MyPref";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    private EditText edtUsername, edtpassword;
    private Button btnLogin;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.login_edt_username);
        edtpassword = findViewById(R.id.login_edt_password);
        btnLogin = findViewById(R.id.login_btn_login);

        sharedPreferences = getSharedPreferences(PREF_FILENAME, MODE_PRIVATE);
        prefEditor = sharedPreferences.edit();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = edtUsername.getText().toString().trim();
                String pass = edtpassword.getText().toString().trim();

                prefEditor.putString(KEY_USERNAME, uname);
                prefEditor.putString(KEY_PASSWORD, pass);
                prefEditor.apply();
                Toast.makeText(LoginActivity.this, "Login Successful! Credentials stored.", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(LoginActivity.this, ListActivity.class));
                finish();

            }
        });

    }
}
