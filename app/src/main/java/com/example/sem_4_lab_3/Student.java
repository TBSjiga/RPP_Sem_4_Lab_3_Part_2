package com.example.sem_4_lab_3;

public class Student {
    int id;
    String name;
    String date;
    String second_name;
    String part;

    public Student(String name, String date){
        this.name = name;
        this.date = date;
    }

    public Student(){}

    public Student(int _id, String _name, String _date){
        this.id = _id;
        this.name = _name;
        this.date = _date;
    }

    public Student(String name, String second_name, String part, String date){
        this.name = name;
        this.second_name = second_name;
        this.part = part;
        this.date = date;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getSecond_name(){
        return second_name;
    }
    public void setSecond_name(String second_name){
        this.second_name = second_name;
    }

    public String getPart(){
        return part;
    }
    public void setPart(String part){
        this.part = part;
    }
}
