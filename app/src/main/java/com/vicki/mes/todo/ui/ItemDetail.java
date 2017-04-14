package com.vicki.mes.todo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vicki.mes.todo.App;
import com.vicki.mes.todo.Models.BucketList;
import com.vicki.mes.todo.R;

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
    @BindView(R.id.detail_postpone)
    Button detailPostpone;
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
                detailDate.setText(item.getDate());
                detailTime.setText(item.getTime());







        }


    }
}
