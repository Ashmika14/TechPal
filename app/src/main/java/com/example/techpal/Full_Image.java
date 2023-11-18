package com.example.techpal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Full_Image extends AppCompatActivity {
    ImageView imageView;
    Workmodel workmodel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof Workmodel){
            workmodel = (Workmodel) object;
        }
        imageView=findViewById(R.id.dimage);


        if (workmodel != null) {
            Glide.with(getApplicationContext()).load(workmodel.getUri()).into(imageView);
        }
    }
}