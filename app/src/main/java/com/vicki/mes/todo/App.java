package com.vicki.mes.todo;

import com.orm.SugarApp;
import com.orm.SugarContext;


public class App extends SugarApp {

    private static App sInstance;



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
