package com.example.ulangan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Input extends AppCompatActivity{

    EditText etxJudul, etxDeskripsi;
    Button btnUpdate, btnDelete;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        etxJudul = findViewById(R.id.etxJudul);
        etxDeskripsi = findViewById(R.id.etxDeskripsi);
//        etxTanggal = findViewById(R.id.etxTanggal);
        btnUpdate = findViewById(R.id.btnUpdate);
//        btnDelete = findViewById(R.id.btnHapus);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mJudul = etxJudul.getText().toString();
                String mDeskripsi = etxDeskripsi.getText().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy' 'hh:mm:ss");
                String mTanggal = dateFormat.format(new Date());

                if (btnUpdate.getText().equals("Simpan")){
                    if (mJudul.equals("")){
                        etxJudul.setError("Silahkan masukkan Nama");
                        etxJudul.requestFocus();
                    }
                    else if (mDeskripsi.equals("")){
                        etxDeskripsi.setError("Silahkan masukkan Email");
                        etxDeskripsi.requestFocus();
                    }
                    else{
                        submitUser(new Item(mTanggal, mJudul, mDeskripsi));
                    }
                }
            }
        });
    }

    private void submitUser(Item request){
        databaseReference.child("Item").push().setValue(request).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                etxJudul.setText("");
                etxDeskripsi.setText("");

                Toast.makeText(Input.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
