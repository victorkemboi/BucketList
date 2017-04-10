package com.vicki.mes.todo.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.vicki.mes.todo.Database.MySQLiteHelper;
import com.vicki.mes.todo.Models.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kembo on 4/3/2017.
 */

public class ToDoAdapter {
        // com.vicki.mes.todo.Database fields
        private SQLiteDatabase database;
        private MySQLiteHelper dbHelper;
        private String[] allColumns = {
                MySQLiteHelper.COLUMN_ID,
                MySQLiteHelper.COLUMN_TITLE ,
                MySQLiteHelper.COLUMN_DESCRIPTION ,
                MySQLiteHelper.COLUMN_DATE ,
                MySQLiteHelper.COLUMN_TIME
        };

        public ToDoAdapter(Context context) {
            dbHelper = new MySQLiteHelper(context);
        }

        public void open() throws SQLException {
            database = dbHelper.getWritableDatabase();
        }

        public void close() {
            dbHelper.close();
        }

        public ToDo createToDo(String title, String description, String date, String time) {
            ContentValues values = new ContentValues();
            values.put(MySQLiteHelper.COLUMN_TITLE, title);
            values.put(MySQLiteHelper.COLUMN_DESCRIPTION, description);
            values.put(MySQLiteHelper.COLUMN_DATE, date);
            values.put(MySQLiteHelper.COLUMN_TIME,time);
            long insertId = database.insert(MySQLiteHelper.TABLE_TODO, null,
                    values);
            Cursor cursor = database.query(MySQLiteHelper.TABLE_TODO,
                    allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                    null, null, null);
            cursor.moveToFirst();
            ToDo newTodo = cursorToToDo(cursor);
            cursor.close();
            return newTodo;
        }

        public void deleteTodo(ToDo todo) {
            long id = todo.getId();
            System.out.println("Comment deleted with id: " + id);
            database.delete(MySQLiteHelper.TABLE_TODO, MySQLiteHelper.COLUMN_ID
                    + " = " + id, null);
        }

        public List<ToDo> getAllTodos() {
            List<ToDo> todos = new ArrayList<ToDo>();

            Cursor cursor = database.query(MySQLiteHelper.TABLE_TODO,
                    allColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ToDo todo = cursorToToDo(cursor);
                todos.add(todo);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
            return todos;
        }

        private ToDo cursorToToDo(Cursor cursor) {
            ToDo todo = new ToDo();
            todo.setId(cursor.getLong(0));
            todo.setTitle(cursor.getString(1));
            todo.setDescription(cursor.getString(2));
            todo.setDate(cursor.getString(3));
            todo.setTime(cursor.getString(4));
            return todo;
        }

}
