package com.example.studentsregisterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentsregisterapp.databinding.ActivityAddStudentBinding;
import com.example.studentsregisterapp.db.StudentsDatabase;
import com.example.studentsregisterapp.db.entity.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddStudentActivity extends AppCompatActivity {

    private AddStudentActivityClickHandlers handlers;
    private ActivityAddStudentBinding binding;
    private StudentsDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        handlers = new AddStudentActivityClickHandlers(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_student);
        binding.setClickHandler(handlers);
        binding.setStudent(new Student("Students name", "Students email", "Students country", ""));

        db = Room.databaseBuilder(getApplicationContext(),
                StudentsDatabase.class, "students_database").allowMainThreadQueries().build();
    }

    public class AddStudentActivityClickHandlers {
        Context context;

        public AddStudentActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onSaveButtonClicked(View view) {
            if (TextUtils.isEmpty(binding.studentNameEditText.getText().toString().trim())) {
                Toast.makeText(AddStudentActivity.this, "Name is missing!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(binding.studentEmailEditText.getText().toString().trim())) {
                Toast.makeText(AddStudentActivity.this, "Email is missing!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(binding.studentCountryEditText.getText().toString().trim())) {
                Toast.makeText(AddStudentActivity.this, "Country is missing!", Toast.LENGTH_SHORT).show();
                return;
            }

            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c);

            db.studentDAO().insertStudent(new Student(binding.studentNameEditText.getText().toString()
                    , binding.studentEmailEditText.getText().toString()
                    , binding.studentCountryEditText.getText().toString()
                    , formattedDate));
            setResult(1);
            finish();
        }
    }
}
