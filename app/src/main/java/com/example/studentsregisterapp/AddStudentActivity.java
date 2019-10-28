package com.example.studentsregisterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentsregisterapp.db.StudentsDatabase;
import com.example.studentsregisterapp.db.entity.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddStudentActivity extends AppCompatActivity {

    private Button saveButton;
    private TextView studentsNameEditText;
    private TextView studentsEmailEditText;
    private TextView studentsCountryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        saveButton = findViewById(R.id.save_button);
        studentsNameEditText = findViewById(R.id.student_name_edit_text);
        studentsEmailEditText = findViewById(R.id.student_email_edit_text);
        studentsCountryEditText = findViewById(R.id.student_country_edit_text);

        final StudentsDatabase db = Room.databaseBuilder(getApplicationContext(),
                StudentsDatabase.class, "students_database").allowMainThreadQueries().build();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(studentsNameEditText.getText().toString().trim())) {
                    Toast.makeText(AddStudentActivity.this, "Name is missing!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(studentsEmailEditText.getText().toString().trim())) {
                    Toast.makeText(AddStudentActivity.this, "Email is missing!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(studentsCountryEditText.getText().toString().trim())) {
                    Toast.makeText(AddStudentActivity.this, "Country is missing!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);

                db.studentDAO().insertStudent(new Student(studentsNameEditText.getText().toString()
                        , studentsEmailEditText.getText().toString()
                        , studentsCountryEditText.getText().toString()
                        , formattedDate));
                setResult(1);
                finish();
            }
        });
    }
}
