package com.example.techpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Student_Online extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    List<Onlinemodel> onlinemodelList;
    Onlineadapter onlineadapter;
    Onlinemodel onlinemodel ;
    TextView clas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_online);
        onlinemodel=new Onlinemodel();

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.onlinerec);

        SharedPreferences sharedPreferences = getSharedPreferences("Student", MODE_PRIVATE);

        // Retrieve data from SharedPreferences
        String name = sharedPreferences.getString("classname", "");

        // Display the data in the receiving activity
        TextView textViewData = findViewById(R.id.dtext);
        textViewData.setText("Name: " +  name);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        onlinemodelList = new ArrayList<Onlinemodel>();
        onlineadapter = new Onlineadapter(getApplicationContext(), onlinemodelList);
        recyclerView.setAdapter(onlineadapter);

        firebaseFirestore.collection("Onlineclass").whereEqualTo("classname",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                    Onlinemodel onlinemodel = documentSnapshot.toObject(Onlinemodel.class);
                    onlinemodelList.add(onlinemodel);
                    onlineadapter.notifyDataSetChanged();
                }
            }
        });





    }
}