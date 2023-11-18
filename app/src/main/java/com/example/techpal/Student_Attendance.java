package com.example.techpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Student_Attendance extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    List<Attendancemodel> attendancemodelList;
    Attendaceadapter attendaceadapter;
    Attendancemodel attendancemodel ;
    TextView clas;

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);
        spinner = findViewById(R.id.select_month);



        attendancemodel=new Attendancemodel();

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.student_workdata);

        SharedPreferences sharedPreferences = getSharedPreferences("Student", MODE_PRIVATE);

        // Retrieve data from SharedPreferences
        String name = sharedPreferences.getString("rollnumber", "");

        // Display the data in the receiving activity
        TextView textViewData = findViewById(R.id.ttclass);
        textViewData.setText("Name: " +  name);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

        attendancemodelList = new ArrayList<Attendancemodel>();
        attendaceadapter = new Attendaceadapter(getApplicationContext(), attendancemodelList);
        recyclerView.setAdapter(attendaceadapter);






        // Create an ArrayAdapter using a string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_months, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Set a listener to handle item selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle item selection here
                if (position == 1) {
                    // Item 1 is selected, change to another activity


                    //   String classname = getIntent().getStringExtra("classname");



                    firebaseFirestore.collection("Attendance").whereEqualTo("month","January").whereEqualTo("rollnumber",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                Attendancemodel attendancemodel = documentSnapshot.toObject(Attendancemodel.class);
                                attendancemodelList.add(attendancemodel);
                                attendaceadapter.notifyDataSetChanged();
                            }
                        }
                    });



                } else if (position == 2) {
                    // Item 2 is selected, perform a different action
                    firebaseFirestore.collection("Attendance").whereEqualTo("month","February").whereEqualTo("rollnumber",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                Attendancemodel attendancemodel = documentSnapshot.toObject(Attendancemodel.class);
                                attendancemodelList.add(attendancemodel);
                                attendaceadapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
                else if (position == 3) {
                    // Item 2 is selected, perform a different action
                    firebaseFirestore.collection("Attendance").whereEqualTo("month","March").whereEqualTo("rollnumber",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                Attendancemodel attendancemodel = documentSnapshot.toObject(Attendancemodel.class);
                                attendancemodelList.add(attendancemodel);
                                attendaceadapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
                else if (position == 4) {
                    // Item 2 is selected, perform a different action
                    firebaseFirestore.collection("Attendance").whereEqualTo("month","April").whereEqualTo("rollnumber",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                Attendancemodel attendancemodel = documentSnapshot.toObject(Attendancemodel.class);
                                attendancemodelList.add(attendancemodel);
                                attendaceadapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
                else if (position == 5) {
                    // Item 2 is selected, perform a different action
                    firebaseFirestore.collection("Attendance").whereEqualTo("month","May").whereEqualTo("rollnumber",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                Attendancemodel attendancemodel = documentSnapshot.toObject(Attendancemodel.class);
                                attendancemodelList.add(attendancemodel);
                                attendaceadapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
                else if (position == 6) {
                    // Item 2 is selected, perform a different action
                    firebaseFirestore.collection("Attendance").whereEqualTo("month","June").whereEqualTo("rollnumber",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                Attendancemodel attendancemodel = documentSnapshot.toObject(Attendancemodel.class);
                                attendancemodelList.add(attendancemodel);
                                attendaceadapter.notifyDataSetChanged();
                            }
                        }
                    });
                } else if (position == 7) {
                    // Item 2 is selected, perform a different action
                    firebaseFirestore.collection("Attendance").whereEqualTo("month","July").whereEqualTo("rollnumber",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                Attendancemodel attendancemodel = documentSnapshot.toObject(Attendancemodel.class);
                                attendancemodelList.add(attendancemodel);
                                attendaceadapter.notifyDataSetChanged();
                            }
                        }
                    });
                }

                else if (position == 8) {
                    // Item 2 is selected, perform a different action
                    firebaseFirestore.collection("Attendance").whereEqualTo("month","August").whereEqualTo("rollnumber",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                Attendancemodel attendancemodel = documentSnapshot.toObject(Attendancemodel.class);
                                attendancemodelList.add(attendancemodel);
                                attendaceadapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
                else if (position == 9) {
                    // Item 2 is selected, perform a different action
                    firebaseFirestore.collection("Attendance").whereEqualTo("month","September").whereEqualTo("rollnumber",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                Attendancemodel attendancemodel = documentSnapshot.toObject(Attendancemodel.class);
                                attendancemodelList.add(attendancemodel);
                                attendaceadapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
                else if (position == 10) {
                    // Item 2 is selected, perform a different action
                    firebaseFirestore.collection("Attendance").whereEqualTo("month","October").whereEqualTo("rollnumber",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                Attendancemodel attendancemodel = documentSnapshot.toObject(Attendancemodel.class);
                                attendancemodelList.add(attendancemodel);
                                attendaceadapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
                else if (position == 11) {
                    // Item 2 is selected, perform a different action
                    firebaseFirestore.collection("Attendance").whereEqualTo("month","November").whereEqualTo("rollnumber",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                Attendancemodel attendancemodel = documentSnapshot.toObject(Attendancemodel.class);
                                attendancemodelList.add(attendancemodel);
                                attendaceadapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
                else if (position == 12) {
                    // Item 2 is selected, perform a different action
                    firebaseFirestore.collection("Attendance").whereEqualTo("month","December").whereEqualTo("rollnumber",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                Attendancemodel attendancemodel = documentSnapshot.toObject(Attendancemodel.class);
                                attendancemodelList.add(attendancemodel);
                                attendaceadapter.notifyDataSetChanged();
                            }
                        }
                    });
                }




                else {
                    Toast.makeText(Student_Attendance.this, "Please Select Month", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle no selection here
            }
        });

    }
}