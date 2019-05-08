package com.example.sem_4_lab_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper implements IDatabaseHandler {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "students";

    //Student
    private static final String TABLE_STUDENT = "student";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";

    private static final String TABLE_2_STUDENT = "student2";
    private static final String KEY_SECOND_NAME = "second_name";
    private static final String KEY_PATR = "patr";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + "(" +
            KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " text," + KEY_DATE + " text" + ")";

    private static final String CREATE_TABLE_STUDENT2 = "CREATE TABLE " + TABLE_2_STUDENT + "(" +
            KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " text," + KEY_SECOND_NAME + " text," + KEY_PATR + " text," + KEY_DATE + " text" + ")";

    private static final String SQL_TAG = "insert into " + TABLE_2_STUDENT + " values (?, ?, ?, ?, ?)";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    public void onCreate2(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT2);
    }

    @Override
    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, student.getName());
        values.put(KEY_DATE, String.valueOf(student.getDate()));

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    public void add2Student(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, student.getName());
        values.put(KEY_SECOND_NAME, student.getSecond_name());
        values.put(KEY_PATR, student.getPart());
        values.put(KEY_DATE, String.valueOf(student.getDate()));

        db.insert(TABLE_2_STUDENT, null, values);
        db.close();
    }

    @Override
    public Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENT, new String[]{
                KEY_ID, KEY_NAME, KEY_DATE}, KEY_ID + "=?", new String[]{
                String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Student student = new Student(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return student;
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<Student>();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();

                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setDate(cursor.getString(2));

                studentList.add(student);
            } while (cursor.moveToNext());
        }

        return studentList;
    }

    public List<Student> getAllStudent2() {
        List<Student> studentList = new ArrayList<Student>();
        String selectQuery = "SELECT * FROM " + TABLE_2_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();

                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setSecond_name(cursor.getString(2));
                student.setPart(cursor.getString(3));
                student.setDate(cursor.getString(4));

                studentList.add(student);
            } while (cursor.moveToNext());
        }

        return studentList;
    }

    @Override
    public int getCountStudent() {
        String countQuery = "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    @Override
    public int updateStudent(Student student, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, student.getName());
        values.put(KEY_DATE, student.getDate());

        return db.update(TABLE_STUDENT, values, KEY_ID + " = ?", new String[]{
                String.valueOf(id)
        });
    }

    @Override
    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ID + " = ?", new String[]{String.valueOf(student.getId())});
        db.close();
    }

    @Override
    public void deleteAllStudent() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int id;
        String name, name_s, second_name, patr, date, id_s;

        List<Student> students = new ArrayList<Student>();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;

        db.beginTransaction();
        try {
            if (oldVersion == 1 && newVersion == 2) {
                Cursor cursor = db.rawQuery(selectQuery, null);

                onCreate2(db);

                if (cursor.moveToFirst()) {
                    do {
                        Student student = new Student();

                        student.setId(Integer.parseInt(cursor.getString(0)));
                        student.setName(cursor.getString(1));
                        student.setDate(cursor.getString(2));

                        id = student.getId();
                        id_s = String.valueOf(id);
                        name = student.getName();
                        date = student.getDate();

                        String[] array_student = name.split(" ");

                        name_s = array_student[0];
                        second_name = array_student[1];
                        patr = array_student[2];

                        students.add(student);

                        db.execSQL(SQL_TAG, new String[]{id_s, name_s, second_name, patr, date});
                    } while (cursor.moveToNext());
                }

                db.execSQL("DROP TABLE " + TABLE_STUDENT);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
