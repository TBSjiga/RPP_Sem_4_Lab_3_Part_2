package com.example.sem_4_lab_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Activity_Second extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ItemStudent> studentArrayList;

    DatabaseHandler db;

    String name, name_s, second_name, part;

    List<Student> students_local;

    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        db = new DatabaseHandler(this);

        flag = getIntent().getIntExtra("flag", flag);
        createList();
        buildRecyclerView();


    }

    public void createList(){
        studentArrayList = new ArrayList<>();

        if (flag == 0) {
            students_local = db.getAllStudent();
        }else if (flag == 1){
            students_local = db.getAllStudent2();
        }

        for (Student st : students_local){
            name_s = st.getName();
            second_name = st.getSecond_name();
            part = st.getPart();

            name = name_s + " " + second_name + " " + part;

            if (flag == 0) {
                studentArrayList.add(new ItemStudent(st.getName(), st.getDate()));
            }else if (flag == 1){
                studentArrayList.add(new ItemStudent(name, st.getDate()));
            }
        }
    }

    public void buildRecyclerView(){
        recyclerView = findViewById(R.id.recyclerview_row);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new StudentAdapter(studentArrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void insertItem(int position, String name, String date){
        studentArrayList.add(position, new ItemStudent(name, date));
        adapter.notifyDataSetChanged();
    }
}
