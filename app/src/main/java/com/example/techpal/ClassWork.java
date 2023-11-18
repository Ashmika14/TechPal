package com.example.techpal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClassWork extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Activity mContext = this;
    private static int lastGeneratedNumber = 0;
    Spinner spinner;
    String[] page = {"Choose Option", "Nursury", "LKG", "UKG", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN"};
    TextView textView;


    Spinner spinnerwork;
    String[] work = {"Choose Option", "homework", "classwork", "assignment"};
    TextView worktextView;


    ///
    ImageView uploadPicIV;
    EditText name, partname, describe, sdate, session, teacher, cclass;
    private ProgressBar uploadProgressBar;
    final int IMAGE_REQUEST = 71;
    Uri imageLocationPath;
    String id;
    StorageReference objectStorageReference;
    FirebaseFirestore objectFirebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_work);

        spinner = findViewById(R.id.spineer);
        spinnerwork = findViewById(R.id.spineerwork);

        partname = findViewById(R.id.partname);
        describe = findViewById(R.id.describe);
        sdate = findViewById(R.id.sdate);
        session = findViewById(R.id.session);
        teacher = findViewById(R.id.teachername);
        cclass = findViewById(R.id.teachername);

        textView = findViewById(R.id.text);
        worktextView = findViewById(R.id.worktext);


        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, page);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //work
        spinnerwork.setOnItemSelectedListener(this);
        ArrayAdapter workadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, work);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerwork.setAdapter(workadapter);


        ///
        //  uploadPicET = findViewById(R.id.imageNameET);
        uploadPicIV = findViewById(R.id.imageID);



        name = findViewById(R.id.imageNameET);
        uploadProgressBar = findViewById(R.id.progress_bar);

        objectStorageReference = FirebaseStorage.getInstance().getReference("imageFolder"); // Create folder to Firebase Storage
        objectFirebaseFirestore = FirebaseFirestore.getInstance();

    }

    public void selectImage(View view) {
        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent, IMAGE_REQUEST);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == IMAGE_REQUEST
                    && resultCode == RESULT_OK
                    && data != null
                    && data.getData() != null) {
                imageLocationPath = data.getData();
                Bitmap objectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageLocationPath);
                uploadPicIV.setImageBitmap(objectBitmap);

            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadImage(View view) {
        try {
            if (/*!uploadPicET.getText().toString().isEmpty() && */imageLocationPath != null) {
                //String nameOfimage = "." + getExtension(imageLocationPath);
                String uuid = UUID.randomUUID().toString();
                String nameOfimage = uuid.toString() + "." + getExtension(imageLocationPath);
                uploadProgressBar.setVisibility(View.VISIBLE);
                uploadProgressBar.setIndeterminate(true);
                final StorageReference imageRef = objectStorageReference.child(nameOfimage);

                UploadTask objectUploadTask = imageRef.putFile(imageLocationPath);

                objectUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {

                            throw task.getException();

                        }
                        return imageRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
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
                            String part = partname.getText().toString();
                            String detail = describe.getText().toString();
                            String submitdate = sdate.getText().toString();
                            String secsion = session.getText().toString();
                            String name = teacher.getText().toString();

                            Map<String, Object> objectMap = new HashMap<>();
                            String docId = objectFirebaseFirestore.collection("data").document().getId();
                            objectMap.put("uri", task.getResult().toString());
                            objectMap.put("subject", subject);
                            objectMap.put("part", part);
                            objectMap.put("detail", detail);
                            objectMap.put("sdate", submitdate);
                            objectMap.put("secssion", secsion);
                            objectMap.put("teacher", name);
                            objectMap.put("classname", typename);
                            objectMap.put("work", typework);
                            objectMap.put("docId", docId);

                            objectMap.put("date", saveCurrentDate);
                            objectMap.put("time", saveCurrenttime);
                            objectMap.put("delete","0");

                            objectMap.put("docId",uuid);
                            objectFirebaseFirestore.collection("Work").document(uuid)
                                    .set(objectMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            uploadProgressBar.setVisibility(View.GONE);
                                            AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                            alert.setMessage("Work Upload Sucessful");
                                            alert.setCancelable(false);
                                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();

                                                    Intent intent = new Intent(ClassWork.this, Teacher_Dashboard.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            });
                                            alert.show();
                                            //Toast.makeText(Class_Work.this, "Upload Sucessfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                        } else if (!task.isSuccessful()) {
                            Toast.makeText(ClassWork.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            } else {
                Toast.makeText(this, "Please provide name for image", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String getExtension(Uri uri) {
        try {
            ContentResolver objectContentResolver = getContentResolver();
            MimeTypeMap objectMimeTypeMap = MimeTypeMap.getSingleton();

            return objectMimeTypeMap.getExtensionFromMimeType(objectContentResolver.getType(uri));

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;


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