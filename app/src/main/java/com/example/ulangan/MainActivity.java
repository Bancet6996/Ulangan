package com.example.ulangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    private ArrayList<Item> requestArrayList;
    private RequestAdapterRecyclerView requestAdapterRecyclerView;
    private RecyclerView list_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        list_request = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list_request.setLayoutManager(mLayoutManager);

        databaseReference.child("Item").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                requestArrayList = new ArrayList<>();

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    Item request = noteDataSnapshot.getValue(Item.class);
                    request.setKey(request.getKey());

                    requestArrayList.add(request);
                }

                requestAdapterRecyclerView = new RequestAdapterRecyclerView(requestArrayList, MainActivity.this);
                list_request.setAdapter(requestAdapterRecyclerView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+""+databaseError.getMessage());
            }
        });

        findViewById(R.id.float_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Input.class));
            }
        });
    }
}
