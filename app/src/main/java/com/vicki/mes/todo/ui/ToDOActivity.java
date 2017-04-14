package com.vicki.mes.todo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vicki.mes.todo.Adapters.todoListAdapter;
import com.vicki.mes.todo.App;
import com.vicki.mes.todo.Models.BucketList;
import com.vicki.mes.todo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ToDOActivity extends AppCompatActivity  implements OptionsFragment.OnCompleteListener{

    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter<String> itemsAdapter;
    @BindView(R.id.lv_to_do)
    ListView lvItems;
    @BindView(R.id.btn_add)
    FloatingActionButton btnAdd;
    todoListAdapter listItem;
    @BindView(R.id.todo_empty_view)
    ViewGroup emptyView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        ButterKnife.bind(this);
        loadlist();
        setUpListViewListener();
        setupOnItemselected();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }
    private  void setupOnItemselected(){
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ItemDetail.class);
                final BucketList b = (BucketList) lvItems.getItemAtPosition(position);
                App app = App.getInstance();
                app.selectedBucketList = b;
                startActivity(intent);
            }
        });
    }


    private void setUpListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                       /* items.remove(position);
                        itemsAdapter.notifyDataSetChanged(); */
                        final BucketList b = (BucketList) lvItems.getItemAtPosition(position);
                        App app = App.getInstance();
                        app.selectedBucketList = b;
                        app.position =position;
                        optionsFrag();
                        return true;
                    }
                }
        );
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadlist();
        setupOnItemselected();
        setUpListViewListener();
    }


    @Override
    protected void onPause() {

        super.onPause();
    }

    void loadlist() {
        List<BucketList> allbucketlists = BucketList.gettodos();
        listItem = new todoListAdapter(this, allbucketlists);
        lvItems.setEmptyView(emptyView);
        lvItems.setAdapter(listItem);

    }

    @OnClick(R.id.btn_add)
    void add() {
        Intent intent = new Intent(this, AddToDoActivity.class);
        startActivity(intent);
    }
    private void optionsFrag() {
        FragmentManager fm =  getSupportFragmentManager();
        OptionsFragment optionFragment = OptionsFragment.newInstance();
        optionFragment.show(fm,"Options:");

    }


    @Override
    public void onComplete(int option) {
        if(option==1){
            App app = App.getInstance();
            app.selectedBucketList.delete();
            loadlist();
         }
    }
}
