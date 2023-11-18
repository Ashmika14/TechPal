package com.example.techpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class A_Work extends AppCompatActivity {
    private ProgressBar progressBar;
    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    List<Workmodel> workmodelList;
    Workadapter workadapter;
    Workmodel workmodel ;
    TextView clas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awork);

        progressBar = findViewById(R.id.progressBar);
        workmodel=new Workmodel();

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.workrec);

        SharedPreferences sharedPreferences = getSharedPreferences("Student", MODE_PRIVATE);

        // Retrieve data from SharedPreferences
        String name = sharedPreferences.getString("classname", "");

        // Display the data in the receiving activity
        TextView textViewData = findViewById(R.id.dclass);
        textViewData.setText("Name: " +  name);





        //   String classname = getIntent().getStringExtra("classname");

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        workmodelList = new ArrayList<Workmodel>();
        workadapter = new Workadapter(getApplicationContext(), workmodelList);
        recyclerView.setAdapter(workadapter);



        loadFirestoreData();




    }

    private void loadFirestoreData() {
        progressBar.setVisibility(View.VISIBLE);
        SharedPreferences sharedPreferences = getSharedPreferences("Student", MODE_PRIVATE);

        // Retrieve data from SharedPreferences
        String name = sharedPreferences.getString("classname", "");

        // Display the data in the receiving activity
        TextView textViewData = findViewById(R.id.dclass);
        textViewData.setText("Name: " +  name);

        firebaseFirestore.collection("Work").whereEqualTo("work","assignment").whereEqualTo("classname",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    workmodelList.clear();
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                        Workmodel workmodel = documentSnapshot.toObject(Workmodel.class);
                        workmodelList.add(workmodel);
                    }
                    workadapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);

                }else {

                }
                // Hide progress bar


            }
        });


    }
}