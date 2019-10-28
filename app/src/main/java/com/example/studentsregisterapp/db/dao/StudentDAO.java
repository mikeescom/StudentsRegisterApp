package com.example.studentsregisterapp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.studentsregisterapp.db.entity.Student;

import java.util.List;

@Dao
public interface StudentDAO {
    @Query("SELECT * FROM Student")
    List<Student> getAllStudents();

    @Query("SELECT * FROM Student WHERE id IN (:studentId)")
    Student getStudent(int studentId);

    @Insert
    Long insertStudent(Student student);

    @Delete
    void delete(Student student);
}
