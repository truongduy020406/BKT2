package com.example.footapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    private EditText email, pass;
    private Button btnlogin, btnregister;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth=FirebaseAuth.getInstance();

        email = findViewById(R.id.login_email);
        pass = findViewById(R.id.login_password);
        btnlogin = findViewById(R.id.login_button_login);
        btnregister = findViewById(R.id.login_button_register);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register(){
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }

    private void login(){
        String edtEmail, edtPass;
        edtEmail = email.getText().toString();
        edtPass = pass.getText().toString();

        if (TextUtils.isEmpty(edtEmail)){
            Toast.makeText(this, "Vui lòng nhập email !!", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(edtPass)){
            Toast.makeText(this, "Vui lòng nhập password !!", Toast.LENGTH_SHORT).show();
        }
        mAuth.signInWithEmailAndPassword(edtEmail,edtPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}