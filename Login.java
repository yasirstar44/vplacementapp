package com.example.place;

import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static EditText email;
    EditText pass;
    Button button;
    FloatingActionButton Abc;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signinstudent);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");
        email=(EditText)findViewById(R.id.editTextstudent);
        pass=(EditText)findViewById(R.id.editTextPasswordstudent);
        button=(Button)findViewById(R.id.buttonSigninstudent);
        Abc=(FloatingActionButton)findViewById(R.id.floatingActionButton) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinstudent(email.getText().toString(),pass.getText().toString());
            }
        });
    }

    private void signinstudent (final String username, final String password) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(username).exists()) {
                    sup login=dataSnapshot.child(username).getValue(sup.class);
                    if(login.getPassword().equals(password))
                    {

                        Intent intent = new Intent(Login.this, listpages.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(Login.this, "INCORRECT PASSWORD OR USERNAME", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Login.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void backtomain(View view) {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }
}

