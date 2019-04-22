package com.example.place;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class managementclas extends AppCompatActivity implements View.OnClickListener{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText email;
    EditText pass,cf;
    Button button, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");
        email=(EditText)findViewById(R.id.editTextEmail2);
        pass=(EditText)findViewById(R.id.editTextPassword2);
        b=(Button) findViewById(R.id.SigninStudent);
        button=(Button)findViewById(R.id.SignupStudent);
        b.setOnClickListener(this);
        button.setOnClickListener(this);
    }


    public void studentregister() {
        final sup user= new sup(email.getText().toString(),pass.getText().toString());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(email.getText().toString().equals("")||pass.getText().toString().equals(""))
                {
                    Toast.makeText(managementclas.this, "Field Empty", Toast.LENGTH_SHORT).show();
                }
                else if (dataSnapshot.child(user.getEmail()).exists())
                    Toast.makeText(managementclas.this, "The username already exists", Toast.LENGTH_SHORT).show();
                else {
                    databaseReference.child(user.getEmail()).setValue(user);

                        Toast.makeText(managementclas.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(managementclas.this, Login.class);
                        startActivity(intent);


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        if(view == button){
            studentregister();
        }

        if(view == b){
            Intent i=new Intent(managementclas.this, Login.class);
            startActivity(i);
        }
    }

}
