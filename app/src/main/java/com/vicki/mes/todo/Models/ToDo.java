package com.vicki.mes.todo.Models;

import com.orm.SugarRecord;
import com.orm.query.Select;

import java.util.List;

/**
 * Created by kembo on 4/3/2017.
 */

public class ToDo extends SugarRecord{
    private  long id;
    private  String title;
    private  String description;
    private  String date;
    private  String time;

   /** public  ToDo(String title, String description,String date,String time){
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;

    } */


    public long gettodoId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.time = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.time = description;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.time = date;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return title;
    }

    public static List<ToDo> gettodos(){
        return Select.from(ToDo.class).list();

    }

}
