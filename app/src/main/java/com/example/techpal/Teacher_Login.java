package com.example.techpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Teacher_Login extends AppCompatActivity {
    EditText nname,roll,clas;
    Button login;
    private FirebaseFirestore firestore;
    // CollectionReference usersCollection = FirebaseFirestore.getInstance().collection("users");
TextView gotoregiter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);


gotoregiter=findViewById(R.id.gotoregister);
        roll = findViewById(R.id.srollnumber);
        clas = findViewById(R.id.sclass);
        login = findViewById(R.id.slogin);
        firestore = FirebaseFirestore.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {

                                         String rollnumber = roll.getText().toString().trim();
                                         String classname = clas.getText().toString().trim();

                                         firestore.collection("Teacher")
                                                 .whereEqualTo("id", rollnumber) // Replace "Item 1" with the value you want to query
                                                 .whereEqualTo("classname", classname) // Replace 10.0 with the value you want to query
                                                 .limit(1) // Limit the result to one document (assuming you expect only one matching item)
                                                 .get()
                                                 .addOnCompleteListener(task -> {
                                                     if (task.isSuccessful() && !task.getResult().isEmpty()) {
                                                         // Matching document found, go to the next activity
                                                         DocumentSnapshot document = task.getResult().getDocuments().get(0);
                                                         SharedPreferences sharedPreferences = getSharedPreferences("Teacher", Context.MODE_PRIVATE);
                                                         SharedPreferences.Editor editor = sharedPreferences.edit();


                                                         // Add data to SharedPreferences

                                                         editor.putBoolean("isLoggedIn", true);
                                                         // Save the data to SharedPreferences
                                                         editor.apply();



                                                         Intent intent = new Intent(Teacher_Login.this, Teacher_Dashboard.class);
                                                         // Pass any data you` want to send to the next activity using Intent extras
                                                         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                                                         startActivity(intent);
                                                         finish();
                                                     } else {
                                                         // No matching document found, show an error message or handle the case accordingly
                                                         Toast.makeText(getApplication(), "No matching item found", Toast.LENGTH_SHORT).show();
                                                     }
                                                 });

                                     }
                                 }
        );




gotoregiter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Teacher_Login.this, Teacher.class);
        // Pass any data you` want to send to the next activity using Intent extras
        startActivity(intent);
        finish();
    }
});

    }
}