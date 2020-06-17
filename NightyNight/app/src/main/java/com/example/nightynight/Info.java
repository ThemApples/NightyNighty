package com.example.nightynight;

import java.util.ArrayList;

public class Info {

    String title;
    String date;
    String desc;
    String mood;

    public Info()
    {
        mood ="No specific mood";
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setMood(String mood)
    {
        this.mood = mood;
        if(this.mood.equals("Mood"))
        {
            this.mood = "No specific mood";
        }

    }

    public String getTitle()
    {
        return title;
    }

    public String getMood() {
        return mood;
    }

    public String getDate()
    {
        return date;
    }

    public String getDesc()
    {
        return desc;
    }

}
