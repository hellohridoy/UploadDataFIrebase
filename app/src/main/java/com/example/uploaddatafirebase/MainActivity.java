package com.example.uploaddatafirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText name,age;
    Button save,showData;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nameId);
        age = findViewById(R.id.ageId);
        save = findViewById(R.id.save);
        showData=findViewById(R.id.showDataId);
        databaseReference= FirebaseDatabase.getInstance().getReference("student");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Details.class);
                startActivity(intent);
            }
        });
    }

    private void saveData() {
        String nme = name.getText().toString().trim();
        String ag = age.getText().toString().trim();

        String key = databaseReference.push().getKey();
        Student student = new Student(nme,ag);
        databaseReference.child(key).setValue(student);
        Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();

        name.setText("");
        age.setText("");
    }
}