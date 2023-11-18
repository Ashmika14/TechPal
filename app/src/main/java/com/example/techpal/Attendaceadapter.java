package com.example.techpal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Attendaceadapter extends RecyclerView.Adapter<Attendaceadapter.ViewHolder>{

    Context context;
    List<Attendancemodel> attendancemodelList;

    public Attendaceadapter(Context context, List<Attendancemodel> attendancemodelList) {
        this.context = context;
        this.attendancemodelList = attendancemodelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.persent.setText(attendancemodelList.get(position).getPersent()+"Days");
        holder.holiday.setText(attendancemodelList.get(position).getHoliday()+"Days");
        holder.absent.setText(attendancemodelList.get(position).getAbsent()+"Days");
    }

    @Override
    public int getItemCount() {
        return attendancemodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView persent,holiday,absent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            persent=itemView.findViewById(R.id.apersent);
            holiday=itemView.findViewById(R.id.aholiday);
            absent=itemView.findViewById(R.id.aabsent);
        }
    }
}
