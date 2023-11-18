package com.example.techpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Teacher_Dashboard extends AppCompatActivity {
CardView classwork,onlineclas,attendance,anydoubt,sregistration,mycomplain,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        classwork=findViewById(R.id.classwork);
        onlineclas=findViewById(R.id.oclass);
        attendance=findViewById(R.id.atttendace);
        anydoubt=findViewById(R.id.anydobt);
        sregistration=findViewById(R.id.ssregi);
        mycomplain=findViewById(R.id.mcomplain);
        logout=findViewById(R.id.teacherlogout);

        classwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacher_Dashboard.this,ClassWork.class));
            }
        });


        onlineclas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacher_Dashboard.this,OnlineClass.class));

            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacher_Dashboard.this,Attendance.class));

            }
        });

        sregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacher_Dashboard.this,Student_Register.class));

            }
        });

mycomplain.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(Teacher_Dashboard.this,Teacher_Complain.class));

    }
});
        anydoubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacher_Dashboard.this, Teacher_Any_Dobut.class));

            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("School", Context.MODE_PRIVATE);
                sharedPreferences.edit().putBoolean("isLoggedIn", false).apply();

                // Redirect the user to the login activity
                startActivity(new Intent(Teacher_Dashboard.this, MainActivity.class));
                finish();
            }
        });

    }
}