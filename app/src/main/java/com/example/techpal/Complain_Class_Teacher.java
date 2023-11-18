package com.example.techpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Complain_Class_Teacher extends AppCompatActivity {
    EditText roll,name,query;
    Button submit;
    Activity mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_class_teacher);


        roll=findViewById(R.id.aroll);
        name=findViewById(R.id.aname);
        query=findViewById(R.id.aquery);
        submit=findViewById(R.id.asubmit);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String roolnumber = roll.getText().toString();
                String nam = name.getText().toString();;
                String que = query.getText().toString();

                // Create a new user with a first and last name
                String uuid = UUID.randomUUID().toString();
                Map<String, Object> user = new HashMap<>();
                user.put("rollnumber", roolnumber);
                user.put("name", nam);
                user.put("complain", que);
                user.put("docId",uuid);


// Add a new document with a generated ID
                db.collection("TeacherComplain")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                alert.setMessage("Complain Upload Sucessful");
                                alert.setCancelable(false);
                                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                        Intent intent = new Intent(getApplicationContext(), Student_Dashboard.class);
                                        startActivity(intent);
                                    }
                                });
                                alert.show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });



            }
        });

    }
}