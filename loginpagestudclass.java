package com.example.place;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class loginpagestudclass extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup, ButtonabSignup;
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpagestud);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        ButtonabSignup=(Button) findViewById((R.id.ButtonabSignUp));
        progressDialog = new ProgressDialog(this);


        buttonSignup.setOnClickListener(this);
        ButtonabSignup.setOnClickListener(this);
    }

    private void registerUser(){

        //creating a new user
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();


        //checking if success
                        if(TextUtils.isEmpty(email)){
                            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
                            return;
                        }

                        if(TextUtils.isEmpty(password)){
                            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
                            return;
                        }

                        //if the email and password are not empty
                        //displaying a progress dialog

                        progressDialog.setMessage("Registering Please Wait...");
                        progressDialog.show();

                        //creating a new user
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        //checking if success
                                        if(task.isSuccessful()){
                                            //display some message here
                                            Toast.makeText(loginpagestudclass.this,"Successfully registered",Toast.LENGTH_LONG).show();
                                            Intent i=new Intent(loginpagestudclass.this, SigninManagement.class);
                                            startActivity(i);
                                        }else{
                                            //display some message here
                                            Toast.makeText(loginpagestudclass.this,"Registration Error",Toast.LENGTH_LONG).show();
                                        }
                                        progressDialog.dismiss();
                                    }
                                });

                    }

                    @Override
                    public void onClick(View view) {
                        if(view == buttonSignup)
                        registerUser();
                        else if(view == ButtonabSignup) {
                            Intent i = new Intent(loginpagestudclass.this, SigninManagement.class);
                            startActivity(i);
                        }
                }




}
