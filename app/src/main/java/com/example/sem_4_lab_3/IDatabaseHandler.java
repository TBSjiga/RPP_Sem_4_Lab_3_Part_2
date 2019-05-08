package com.example.sem_4_lab_3;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

interface IDatabaseHandler {
    void onCreate(SQLiteDatabase db);
    void onCreate2(SQLiteDatabase db2);

    public void addStudent(Student student);
    public Student getStudent(int id);
    public List<Student> getAllStudent();
    public int getCountStudent();
    public int updateStudent(Student student, int id);
    public void deleteStudent(Student student);
    public void deleteAllStudent();
}
