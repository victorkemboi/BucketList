package com.vicki.mes.todo.Models;

import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

/**
 * Created by kembo on 4/3/2017.
 */

public class BucketList extends SugarRecord {
    private  long id;
    private  String title;
    private  String description;
    private String date;
    private  String time;
    private    String category;
    public String status;

    public BucketList() {
    }

    public  boolean categoryIsSet(){
        if(!this.category.equals(null)){
            return  true;
        }
        return  false;
    };


    public BucketList(String title, String description, String date, String time,String category){
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.category = category;



    }


    public long getid() {
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
    public String getCategory() {
        return category;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public void setCategory(String category) {
        this.category = category;
    }


    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return title;
    }

    public static List<BucketList> getactive(){
        return Select.from(BucketList.class).where(Condition.prop("status").eq("Active")).list();

    }
    public static List<BucketList> getall(){
        return Select.from(BucketList.class).list();

    }
    public static List<BucketList> gettodosonly(){
        return Select.from(BucketList.class).where(Condition.prop("category").eq("Todo"))
                .where(Condition.prop("status").notEq("Active"))
                .list();

    }
    public static List<BucketList> getbucketlistonly(){
        return Select.from(BucketList.class).where(Condition.prop("category").eq("Bucket List"))
                .where(Condition.prop("status").notEq("Active")).list();

    }
    public static List<BucketList> getcompleted(){
        return Select.from(BucketList.class).where(Condition.prop("status").eq("Completed")).list();

    }




}
