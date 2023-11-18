package com.example.techpal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

public class Workadapter extends RecyclerView.Adapter<Workadapter.ViewHolder>{
    Context context;
    List<Workmodel> workmodelList;

    public Workadapter(Context context, List<Workmodel> workmodelList) {
        this.context = context;
        this.workmodelList = workmodelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.work,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(workmodelList.get(position).getUri()).into(holder.imageView);
        holder.subject.setText(workmodelList.get(position).getSubject());
        holder.date.setText(workmodelList.get(position).getDate());
        holder.time.setText(workmodelList.get(position).getTime());
        holder.title.setText(workmodelList.get(position).getPart());
        holder.teacher.setText(workmodelList.get(position).getTeacher());
        holder.description.setText(workmodelList.get(position).getDetail());
        holder.cls.setText(workmodelList.get(position).getClassname());
        holder.work.setText(workmodelList.get(position).getWork());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Full_Image.class);
                intent.putExtra("detail",workmodelList.get(position));
                intent.putExtra("image",workmodelList.get(position).getUri());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return workmodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject,date,time,title,teacher,description,cls,work;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.wsubject);
            date=itemView.findViewById(R.id.wdate);
            time=itemView.findViewById(R.id.wtime);
            title=itemView.findViewById(R.id.wtitle);
            teacher=itemView.findViewById(R.id.wteacher);
            description=itemView.findViewById(R.id.wdescription);
            cls=itemView.findViewById(R.id.wclass);
            work=itemView.findViewById(R.id.wwork);
            imageView=itemView.findViewById(R.id.wimage);

        }
    }
}
