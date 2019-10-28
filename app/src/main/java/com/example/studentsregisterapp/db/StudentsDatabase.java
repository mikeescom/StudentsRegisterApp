package com.example.studentsregisterapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.studentsregisterapp.db.dao.StudentDAO;
import com.example.studentsregisterapp.db.entity.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentsDatabase extends RoomDatabase {
    public abstract StudentDAO studentDAO();
}
