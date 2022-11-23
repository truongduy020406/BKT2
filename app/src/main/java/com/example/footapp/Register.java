package com.example.footapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class Register extends AppCompatActivity {

    private EditText email, pass;
    private Button btnlogin, btnregister;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth=FirebaseAuth.getInstance();

        email = findViewById(R.id.register_email);
        pass = findViewById(R.id.register_password);
        btnlogin = findViewById(R.id.register_button_login);
        btnregister = findViewById(R.id.register_button_register);

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
        String edtEmail, edtPass;
        edtEmail = email.getText().toString();
        edtPass = pass.getText().toString();

        if (TextUtils.isEmpty(edtEmail)){
            Toast.makeText(this, "Vui lòng nhập email !!", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(edtPass)){
            Toast.makeText(this, "Vui lòng nhập password !!", Toast.LENGTH_SHORT).show();
        }
        mAuth.createUserWithEmailAndPassword(edtEmail,edtPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(getApplicationContext(),"tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Register.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"tạo tài khoản không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void login(){
        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
    }
}
