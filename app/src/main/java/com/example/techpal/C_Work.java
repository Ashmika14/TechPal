package com.example.techpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

public class C_Work extends AppCompatActivity {
    private ProgressBar progressBar;
    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    List<Workmodel> workmodelList;
    Workadapter workadapter;
    Workmodel workmodel ;
    TextView clas;
    private int progressStatus = 0;
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cwork);


        progressBar = findViewById(R.id.progressBar);

        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus++;
                handler.post(() -> progressBar.setProgress(progressStatus));
                try {
                    Thread.sleep(100); // Simulate delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        workmodel=new Workmodel();

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.workrec);


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





        //   String classname = getIntent().getStringExtra("classname");

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        workmodelList = new ArrayList<Workmodel>();
        workadapter = new Workadapter(getApplicationContext(), workmodelList);
        recyclerView.setAdapter(workadapter);





        firebaseFirestore.collection("Work").whereEqualTo("work","classwork").whereEqualTo("classname",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                    Workmodel workmodel = documentSnapshot.toObject(Workmodel.class);
                    workmodelList.add(workmodel);
                    workadapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}