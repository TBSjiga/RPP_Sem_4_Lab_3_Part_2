package com.example.sem_4_lab_3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db;

    Button button_conclusion;
    Button button_insert;
    Button button_update;

    String name, date;

    List<Student> students;

    EditText name_student;

    Date date_now;

    int count_List, flag = 1, version;
    String name_s, second_name, part;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

        //database = db.getWritableDatabase();

        //version = database.getVersion();

        if (version == 1){
            flag = 0;
        }else if (version == 2){
            flag = 1;
        }

        //db.deleteAllStudent();

        //database = db.getWritableDatabase();

        date_now = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss E dd.MM.yyyy");

        date = simpleDateFormat.format(date_now);

        db.addStudent(new Student("Dmitrii Alekseev Aleksandrovich", date));
        db.addStudent(new Student("Nikita Trofimov Andreevich", date));
        db.addStudent(new Student("Maksim Kovalev Sergeevich", date));
        db.addStudent(new Student("Rustam Sagdeev Robertovich", date));
        db.addStudent(new Student("Andrey Gorbachev Dmitrievich", date));

        button_conclusion = (Button)findViewById(R.id.button);
        button_conclusion.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        button_insert = (Button)findViewById(R.id.button2);
        button_insert.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final View view1 = getLayoutInflater().inflate(R.layout.create_student, null);

                name_student = (EditText)view1.findViewById(R.id.name_student);
                Button create = (Button)view1.findViewById(R.id.button_create);

                builder.setView(view1);

                final AlertDialog dialog = builder.create();
                dialog.show();

                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = String.valueOf(name_student.getText().toString());

                        date_now = new Date();

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss E dd.MM.yyyy");

                        date = simpleDateFormat.format(date_now);

                        String[] array_student = name.split(" ");

                        name_s = array_student[0];
                        second_name = array_student[1];
                        part = array_student[2];

                        if (flag == 0) {
                            db.addStudent(new Student(name, date));
                        }else if (flag == 1){
                            db.add2Student(new Student(name_s, second_name, part, date));
                        }
                        dialog.dismiss();
                    }
                });

                students = db.getAllStudent();

                for (Student st : students){
                    String log = "Id: " + st.getId() + " , Name: " + st.getName() + " , Date: " + st.getDate();

                    System.out.print("Student: ");
                    System.out.println(log);
                }
            }
        });

        button_update = (Button)findViewById(R.id.button3);
        button_update.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                students = db.getAllStudent();

                for (Student st : students){
                    count_List = st.getId();
                }

                date_now = new Date();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd.MM.yyyy");

                date = simpleDateFormat.format(date_now);

                db.updateStudent(new Student("Иванов Иван Иванович", date), count_List);
            }
        });
    }

    public void openActivity(){
        Intent intent = new Intent(this, Activity_Second.class);
        intent.putExtra("flag", flag);
        startActivity(intent);
    }
}