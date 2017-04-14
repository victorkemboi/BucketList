package com.vicki.mes.todo.ui;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vicki.mes.todo.Broadcasts.AlarmReceiver;
import com.vicki.mes.todo.Models.BucketList;
import com.vicki.mes.todo.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kembo on 4/3/2017.
 */

public class AddToDoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    BucketList itemBucket;
    final Calendar dateCalender = Calendar.getInstance();
    final Calendar timeCalender = Calendar.getInstance();

    @BindView(R.id.ed_title)
    EditText edTitle;

    @BindView(R.id.title_layout)
    TextInputLayout titleLayout;

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timeCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
        timeCalender.set(Calendar.MINUTE, minute);

        edTime.setText(String.format("%s:%s", timeCalender.get(Calendar.HOUR_OF_DAY),
                validateMinute()));
        selectedTime = edTime.getText().toString();

    }

    @BindView(R.id.ed_description)
    EditText edDescription;

    @BindView(R.id.describe_layout)
    TextInputLayout describeLayout;

    @BindView(R.id.ed_date)
    EditText edDate;

    @BindView(R.id.date_layout)
    TextInputLayout dateLayout;

    @BindView(R.id.ed_time)
    EditText edTime;

    @BindView(R.id.time_layout)
    TextInputLayout timeLayout;

    @BindView(R.id.category_layout)
    TextInputLayout categoryLayout;

    @BindView(R.id.btn_todo_save)
    Button btnSave;

    @BindView(R.id.sp_category)
    MaterialSpinner spCategory;

    private  String selectedCategory = "";
    private  String selectedDate = "";
    private  String selectedTime = "";




    private static final String[] Categories = {"Select Category","Bucket List", "Todo", "Meeting","Alarm", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo_activity);
        ButterKnife.bind(this);
        hideKeyboard();
        spCategory.setItems(Categories);
        spCategory.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if(position>0) {
                    selectedCategory = item.toString();
                }
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("Add Activities.");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


     }

     @OnClick(R.id.btn_todo_save)
     public void ButtonClick(){
         if(is_valid())
         {
             hideKeyboard();
             String title = edTitle.getText().toString().trim();
             String description = edDescription.getText().toString().trim();
             String date = edDate.getText().toString().trim();
             String time = edTime.getText().toString().trim();

                 itemBucket = new BucketList(title,description,date,time,selectedCategory);
                 itemBucket.status = "Active";
                 itemBucket.save();
             Calendar cal = Calendar.getInstance();

             cal.setTimeInMillis(System.currentTimeMillis());
             cal.clear();
             cal.set(dateCalender.get(Calendar.YEAR),dateCalender.get(Calendar.MONTH),dateCalender.get(Calendar.DATE),timeCalender.get(Calendar.HOUR_OF_DAY),timeCalender.get(Calendar.MINUTE));
             cal.set(Calendar.SECOND, 0);
             AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
             Intent intent = new Intent(this, AlarmReceiver.class);
             intent.putExtra("ITEM_ID",itemBucket.getId());
             PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

             alarmMgr.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);


             Snackbar.make(btnSave,"Save Successfull.",Snackbar.LENGTH_LONG).show();
                 finish();


         }
     }

    private boolean is_valid()
    {
        if(validateDate() & validateDescription() & validateTime() & validateTitle()& validateCategory())
        {
            return true;
        }


        return false;


    }
    private boolean validateTitle()
    {
        String title = edTitle.getText().toString().trim();
        if(title.equals(""))
        {
            titleLayout.setError("Enter title.");
            return  false;
        }else {
            titleLayout.setErrorEnabled(false);
        }
        return  true;
    }
    private boolean validateDescription()
    {
        String description = edDescription.getText().toString().trim();
        if(description.equals(""))
        {
            describeLayout.setError("Enter description.");
            return  false;
        }else {
            describeLayout.setErrorEnabled(false);
        }
        return  true;
    }
    private boolean validateDate()
    {
        String date = edDate.getText().toString().trim();
        if(date.equals(""))
        {
            dateLayout.setError("Enter date.");
            return  false;
        }else {
            dateLayout.setErrorEnabled(false);
        }
        return  true;
    }
    private boolean validateTime()
    {
        String time = edTime.getText().toString().trim();
        if(time.equals(""))
        {
            timeLayout.setError("Enter time.");
            return  false;
        }else {
            timeLayout.setErrorEnabled(false);
        }
        return  true;
    }
    private boolean validateCategory()
    {
        if(selectedCategory.equals(""))
        {
            return  false;
        }

        return  true;


    }
    void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @OnClick(R.id.ed_date)
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    @OnClick(R.id.ed_time)
    public void showTimePickerDialog(View v) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "TimePicker");
    }


    // handle the date selected
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        // store the values selected into a Calendar instance

        dateCalender.set(Calendar.YEAR, year);
        dateCalender.set(Calendar.MONTH, monthOfYear);
        dateCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        edDate.setText(String.format("%s/%s/%s",dateCalender.get(Calendar.DAY_OF_MONTH),
                dateCalender.get(Calendar.MONTH),dateCalender.get(Calendar.YEAR)));
        selectedDate = edDate.getText().toString();
    }


    public  String validateMinute(){
        int minute =timeCalender.get(Calendar.MINUTE);
        String returnMinute;
        if(minute==0){returnMinute="00";}
        else if(minute==1){returnMinute="01";}
        else if(minute==2){returnMinute="02";}
        else if(minute==3){returnMinute="03";}
        else if(minute==4){returnMinute="04";}
        else if(minute==5){returnMinute="05";}
        else if(minute==6){returnMinute="06";}
        else if(minute==7){returnMinute="07";}
        else if(minute==8){returnMinute="08";}
        else if(minute==9){returnMinute="09";}
        else{
            returnMinute = String.format("%s",minute);
        }

        return  returnMinute;
    }

    public static void  sendNotification(BucketList item, Context context) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.todo_icon);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.journaldev.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        if(item.getCategory().equals("Alarm")) {
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.alarm));
        }else if(item.getCategory().equals("Meeting")){
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.meeting));
        }else if(item.getCategory().equals("Bucket List")){
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.bucket));
        }else if(item.getCategory().equals("Todo")){
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.todo_icon));
        }else{
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.todo_icon));
        }

        builder.setContentTitle(item.getCategory());
        builder.setContentText(item.getTitle());
        builder.setSubText("Tap to view the website.");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
    }





}