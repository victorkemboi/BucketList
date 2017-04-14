package com.vicki.mes.todo;

import com.orm.SugarApp;
import com.orm.SugarContext;
import com.vicki.mes.todo.Models.BucketList;


public class App extends SugarApp {

    private static App sInstance;

    public BucketList selectedBucketList;
    public  int position;


    public static App getInstance() {
        return sInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        SugarContext.init(this);

    }
    @Override
    public  void  onTerminate(){
        super.onTerminate();
        SugarContext.terminate();
    }







}
