package com.example.studentsregisterapp.db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsregisterapp.R;
import com.example.studentsregisterapp.db.entity.Student;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Student> mData;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(List<Student> data) {
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = mData.get(position);
        holder.studentsName.setText(student.getName());
        holder.studentsEmail.setText(student.getEmail());
        holder.studentsCountry.setText(student.getCountry());
        holder.studentsDate.setText(student.getDate());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView studentsName;
        TextView studentsEmail;
        TextView studentsCountry;
        TextView studentsDate;

        ViewHolder(View itemView) {
            super(itemView);
            studentsName = itemView.findViewById(R.id.students_name);
            studentsEmail = itemView.findViewById(R.id.students_email);
            studentsCountry = itemView.findViewById(R.id.students_country);
            studentsDate = itemView.findViewById(R.id.students_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Student getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
