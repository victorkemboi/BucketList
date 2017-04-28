package com.vicki.mes.todo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vicki.mes.todo.App;
import com.vicki.mes.todo.Models.BucketList;
import com.vicki.mes.todo.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kembo on 4/14/2017.
 */

public class ItemDetail extends AppCompatActivity {
    Long id;
    BucketList item;
    @BindView(R.id.detail_icon)
    ImageView detailIcon;
    @BindView(R.id.detail_title)
    TextView detailTitle;
    @BindView(R.id.detail_description)
    TextView detailDescription;
    @BindView(R.id.detail_date)
    TextView detailDate;
    @BindView(R.id.detail_time)
    TextView detailTime;

    @BindView(R.id.detail_complete)
    Button detailComplete;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_layout);
        ButterKnife.bind(this);

        App app = App.getInstance();
        item = app.selectedBucketList;
        if (item.equals(null)){

            Long intid = getIntent().getLongExtra("ITEM_DETAIL_ID",0);
            item = BucketList.findById(BucketList.class,intid);
        }
            ActionBar actionBar = getSupportActionBar();
            if(actionBar!=null){
                actionBar.setTitle(item.getCategory());
                actionBar.setDisplayHomeAsUpEnabled(true);


                detailTitle.setText(item.getTitle());
                detailDescription.setText(item.getDescription());
                detailDate.setText(String.format("%s/%s/%s",item.getDate().get(Calendar.DATE),item.getDate().get(Calendar.MONTH),item.getDate().get(Calendar.YEAR)));
                detailTime.setText(String.format("%s:%s hrs",item.getDate().getTime().getHours(),item.getDate().getTime().getMinutes()));
                if (item.status.equals("Completed")){
                    detailComplete.setVisibility(View.GONE);
                    detailIcon.setImageResource(R.drawable.completed);
                }else if(item.getCategory().equals("Reminder")){
                    detailIcon.setImageResource(R.drawable.alarm);
                }else if(item.getCategory().equals("Bucket List")){
                    detailIcon.setImageResource(R.drawable.bucket);
                }else if(item.getCategory().equals("Meeting")){
                    detailIcon.setImageResource(R.drawable.meeting);
                }else if(item.getCategory().equals("Other")){
                    detailIcon.setImageResource(R.drawable.other);
                }

        }
        detailComplete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                item.status = "Completed";
                item.save();
                finish();

            }
        });





    }

}
