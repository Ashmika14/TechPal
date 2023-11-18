package com.example.techpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Attendance extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Activity mContext = this;
    private static int lastGeneratedNumber = 0;
    Spinner spinner;
    String[] page = {"Choose Option", "Nursury", "LKG", "UKG", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN"};
    TextView textView;

    Button button;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Spinner spinnerwork;
    String[] work = {"Choose Option", "January", "February", "March","April","May","June","July","August","September","October","November","December"};
    TextView worktextView;


    ///
    ImageView uploadPicIV;
    EditText name, time, recipt,roll,holiday;
    private ProgressBar uploadProgressBar;
    final int IMAGE_REQUEST = 71;
    Uri imageLocationPath;
    String id;
    StorageReference objectStorageReference;
    FirebaseFirestore objectFirebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);



        spinner = findViewById(R.id.spineer);
        spinnerwork = findViewById(R.id.spineerwork);
        name = findViewById(R.id.subject);
        time=findViewById(R.id.tim_);
        recipt=findViewById(R.id.recipt);
        roll=findViewById(R.id.proll);
        holiday=findViewById(R.id.tholiday);
        uploadProgressBar=findViewById(R.id.progress_bar);

        textView = findViewById(R.id.text);
        worktextView = findViewById(R.id.worktext);
        button=findViewById(R.id.uploadBtnt);


        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, page);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //work
        spinnerwork.setOnItemSelectedListener(this);
        ArrayAdapter workadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, work);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerwork.setAdapter(workadapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String saveCurrentDate, saveCurrenttime;
                Calendar calForDate = Calendar.getInstance();


                SimpleDateFormat currentDate = new SimpleDateFormat("MM, dd, yyyy");
                saveCurrentDate = currentDate.format(calForDate.getTime());

                SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss a");

                saveCurrenttime = currenttime.format(calForDate.getTime());


                String mode = recipt.getText().toString();
                String typename = textView.getText().toString();
                String typework = worktextView.getText().toString();
                String subject = name.getText().toString();
                String tim = time.getText().toString();
                String rol = roll.getText().toString();
                String hol = holiday.getText().toString();

                String uuid = UUID.randomUUID().toString();
                Map<String, Object> objectMap = new HashMap<>();

                objectMap.put("Name", subject);
                objectMap.put("persent", tim);
                objectMap.put("holiday", hol);
                objectMap.put("rollnumber", rol);
                objectMap.put("absent", mode);
                objectMap.put("classname", typename);
                objectMap.put("month", typework);
                objectMap.put("date", saveCurrentDate);
                objectMap.put("time", saveCurrenttime);
                objectMap.put("docId",uuid);
                db.collection("Attendance").document(uuid)
                        .set(objectMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                uploadProgressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                alert.setMessage("Attendance Upload Sucessful");
                                alert.setCancelable(false);
                                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                        Intent intent = new Intent(Attendance.this, Teacher_Dashboard.class);
                                        startActivity(intent);
                                        finish();
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







    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = spinner.getSelectedItem().toString();
        textView.setText(text);
        String workt = spinnerwork.getSelectedItem().toString();
        worktextView.setText(workt);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public static class UniqueNumberGenerator {
        private static int lastGeneratedNumber = 0;

        public static int generateUniqueNumber() {
            // Increment the last generated number by 1 to get the new unique number
            lastGeneratedNumber++;
            return lastGeneratedNumber;
        }
    }
}