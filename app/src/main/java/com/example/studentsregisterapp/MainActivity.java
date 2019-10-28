package com.example.studentsregisterapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.studentsregisterapp.databinding.ActivityMainBinding;
import com.example.studentsregisterapp.db.MyRecyclerViewAdapter;
import com.example.studentsregisterapp.db.StudentsDatabase;
import com.example.studentsregisterapp.db.entity.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityClickHandlers handlers;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private List<Student> studentList = new ArrayList<>();
    private ActivityMainBinding binding;
    private StudentsDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handlers = new MainActivityClickHandlers(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setClickHandler(handlers);

        setSupportActionBar(binding.toolbar);

        db = Room.databaseBuilder(getApplicationContext(),
                StudentsDatabase.class, "students_database").allowMainThreadQueries().build();

        recyclerView = binding.recyclerView;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        updateRecyclerView();

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                updateRecyclerView();
            }
        }
    }

    private void updateRecyclerView() {
        studentList = db.studentDAO().getAllStudents();
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(studentList);
        recyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.notifyDataSetChanged();
    }

    public class MainActivityClickHandlers {
        Context context;

        public MainActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onFabButtonClicked(View view) {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivityForResult(intent, 1);
        }
    }
}
