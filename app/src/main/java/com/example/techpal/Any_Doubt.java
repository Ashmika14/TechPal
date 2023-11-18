package com.example.techpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class Any_Doubt extends AppCompatActivity {
    Any_Doubt mContext = this;
    private static int lastGeneratedNumber = 0;
    EditText roll,name,query;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_doubt);
        roll=findViewById(R.id.aroll);
        name=findViewById(R.id.aname);
        query=findViewById(R.id.aquery);
        submit=findViewById(R.id.asubmit);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastGeneratedNumber++;

                String roolnumber = roll.getText().toString();
                String nam = name.getText().toString();;
                String que = query.getText().toString();

                // Create a new user with a first and last name
                String uuid = UUID.randomUUID().toString();
                Map<String, Object> user = new HashMap<>();
                user.put("rollnumber", roolnumber);
                user.put("name", nam);
                user.put("query", que);
                user.put("docId",uuid);


// Add a new document with a generated ID
                db.collection("Anyquery")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                alert.setMessage("Query Upload Sucessful");
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