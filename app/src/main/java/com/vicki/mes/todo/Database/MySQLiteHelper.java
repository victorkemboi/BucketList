package com.vicki.mes.todo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by Vicki_Mes on 4/3/2017.
 *
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todo.db";
    public static final String TABLE_TODO = "tb_todo";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    private static final String TODO_TABLE_CREATE =
            "CREATE TABLE " + TABLE_TODO + " (" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_TITLE + " text not null," +
                    COLUMN_DESCRIPTION + " text not null," +
                    COLUMN_DATE + " date not null," +
                    COLUMN_TIME+ " time not null);"
            ;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TODO_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

}
