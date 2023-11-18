package com.example.techpal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Onlineadapter extends RecyclerView.Adapter<Onlineadapter.ViewHolder>{
    Context context;
    List<Onlinemodel> onlinemodelList;

    public Onlineadapter(Context context, List<Onlinemodel> onlinemodelList) {
        this.context = context;
        this.onlinemodelList = onlinemodelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.onlineclass,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.link.setText(onlinemodelList.get(position).getLink());
        holder.teacher.setText(onlinemodelList.get(position).getTeacher());
        holder.time.setText(onlinemodelList.get(position).getClasstime());

    }

    @Override
    public int getItemCount() {
        return onlinemodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView link,teacher,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            link=itemView.findViewById(R.id.onlink);
            teacher=itemView.findViewById(R.id.oteacher);
            time=itemView.findViewById(R.id.otime);



        }
    }
}
