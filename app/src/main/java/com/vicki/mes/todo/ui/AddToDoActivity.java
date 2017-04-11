package com.vicki.mes.todo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vicki.mes.todo.Models.BucketList;
import com.vicki.mes.todo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kembo on 4/3/2017.
 */

public class AddToDoActivity extends AppCompatActivity {

    @BindView(R.id.ed_title)
    EditText edTitle;

    @BindView(R.id.title_layout)
    TextInputLayout titleLayout;

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



    private static final String[] Categories = {"Select Category","Bucket List", "Todo", "Meeting","Alarm", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo_activity);
        ButterKnife.bind(this);
        hideKeyboard();
        spCategory.setItems(Categories);


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
             final String[] selectedcategory = new String[1];
             spCategory.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                     if (position > 0) {
                         selectedcategory[0] = item.toString();

                     }

                 }
             });


              final BucketList itemBucket = new BucketList(title,description,date,time,selectedcategory[0]);



             if (!itemBucket.equals(null))
             itemBucket.save();
             {
                 Snackbar.make(btnSave,"Save Successfull.",Snackbar.LENGTH_LONG).show();
                 finish();
             }

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
        int selectedcategory = spCategory.getSelectedIndex();

        if (selectedcategory<0){
            categoryLayout.setError("Pick Category.");

    }else{
            categoryLayout.setErrorEnabled(false);
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



}
