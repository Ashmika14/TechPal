package com.example.techpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Student_Dashboard extends AppCompatActivity {
    CardView classwork,onlineclas,attendance,anydoubt,mycomplain,logout,homewor,assignment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        classwork=findViewById(R.id.classwork);
        onlineclas=findViewById(R.id.oclass);
        attendance=findViewById(R.id.atttendace);
        anydoubt=findViewById(R.id.anydobt);
        mycomplain=findViewById(R.id.scomplain);
        logout=findViewById(R.id.teacherlogout);
        homewor=findViewById(R.id.homework);
        assignment=findViewById(R.id.assignmet);

        mycomplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Dashboard.this,Complain_Class_Teacher.class));

            }
        });

        anydoubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Dashboard.this,Any_Doubt.class));

            }
        });


        homewor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Dashboard.this,Home_Work.class));

            }
        });

        assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Dashboard.this,A_Work.class));

            }
        });
        classwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Dashboard.this,C_Work.class));
            }
        });


        onlineclas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Dashboard.this,Student_Online.class));

            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Dashboard.this,Student_Attendance.class));

            }
        });

        mycomplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Dashboard.this,Complain_Class_Teacher.class));

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("School", Context.MODE_PRIVATE);
                sharedPreferences.edit().putBoolean("isLoggedIn", false).apply();

                // Redirect the user to the login activity
                startActivity(new Intent(Student_Dashboard.this, MainActivity.class));
                finish();
            }
        });

    }
}