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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OnlineClass extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Activity mContext = this;
    private static int lastGeneratedNumber = 0;
    Spinner spinner;
    String[] page = {"Choose Option", "Nursury", "LKG", "UKG", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN"};
    TextView textView;

    Button button;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Spinner spinnerwork;
    String[] work = {"Choose Option", "SUNDAY", "MONDAY", "TUESDAY","WEDNESDAY","THUSDAY","FRIDAY","SATURADAY"};
    TextView worktextView;


    ///
    ImageView uploadPicIV;
    EditText name, time, teacher;
    private ProgressBar uploadProgressBar;
    final int IMAGE_REQUEST = 71;
    Uri imageLocationPath;
    String id;
    StorageReference objectStorageReference;
    FirebaseFirestore objectFirebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_class);
        spinner = findViewById(R.id.spineer);
        spinnerwork = findViewById(R.id.spineerwork);
        name = findViewById(R.id.subject);
        time=findViewById(R.id.tim_);

        teacher = findViewById(R.id.teacher_);

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
        uploadProgressBar = findViewById(R.id.progress_bar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lastGeneratedNumber++;


                String saveCurrentDate, saveCurrenttime;
                Calendar calForDate = Calendar.getInstance();


                SimpleDateFormat currentDate = new SimpleDateFormat("MM, dd, yyyy");
                saveCurrentDate = currentDate.format(calForDate.getTime());

                SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss a");


                saveCurrenttime = currenttime.format(calForDate.getTime());



                String typename = textView.getText().toString();
                String typework = worktextView.getText().toString();
                String subject = name.getText().toString();
                String tim = time.getText().toString();
                String tea = teacher.getText().toString();

                String uuid = UUID.randomUUID().toString();
                Map<String, Object> objectMap = new HashMap<>();
                uploadProgressBar.setVisibility(View.VISIBLE);
                objectMap.put("link", subject);
                objectMap.put("classtime", tim);
                objectMap.put("teacher", tea);

                objectMap.put("classname", typename);
                objectMap.put("days", typework);

                objectMap.put("date", saveCurrentDate);
                objectMap.put("time", saveCurrenttime);
                objectMap.put("delete","0");

                objectMap.put("docId",uuid);
                db.collection("Onlineclass").document(uuid).
                        set(objectMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                uploadProgressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                alert.setMessage("Link Upload Sucessful");
                                alert.setCancelable(false);
                                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                        Intent intent = new Intent(OnlineClass.this, Teacher_Dashboard.class);
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