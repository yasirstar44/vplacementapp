package com.example.place;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class listpages extends AppCompatActivity {

    TextView title,desc;
    FirebaseDatabase database;
    DatabaseReference myRef;


    ImageView view;


        //a list to store all the products
        List<company> productList;

        //the recyclerview
        RecyclerView recyclerView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_products);
            title = findViewById(R.id.companytitle);
            desc = findViewById(R.id.description);
            //getting the recyclerview from xml
            recyclerView = (RecyclerView) findViewById(R.id.recycler);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            myRef = FirebaseDatabase.getInstance().getReference("Data");
            company c=new company();
            //initializing the productlist
            productList = new ArrayList<company>();
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                        String user= postSnapshot.child("title").getValue(String.class);
                        String user1= postSnapshot.child("shortdesc").getValue(String.class);
                        String image=postSnapshot.child("image").getValue(String.class);
                        productList.add(
                        new company(user,user1, image));



                } //creating recyclerview adapter
                    companyAdapter adapter = new companyAdapter(listpages.this, productList);

                    //setting adapter to recyclerview
                    recyclerView.setAdapter(adapter);
                }
                @Override
                public void onCancelled(DatabaseError firebaseError) {

                }
            });
        }

    public void uploadd(View view) {
        Intent i=new Intent(listpages.this, uploaddata.class);
        startActivity(i);
    }
}
