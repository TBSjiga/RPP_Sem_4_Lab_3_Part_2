package com.example.sem_4_lab_3;

public class ItemStudent {
    private String mText_name;
    private String mDate_Insert;

    public ItemStudent(String text_name, String date_insert){
        mText_name = text_name;
        mDate_Insert = date_insert;
    }

    public String getmText_name(){
        return mText_name;
    }

    public String getmDate_Insert(){
        return mDate_Insert;
    }
}
