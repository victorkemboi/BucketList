package com.vicki.mes.todo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vicki.mes.todo.Adapters.todoListAdapter;
import com.vicki.mes.todo.Models.BucketList;
import com.vicki.mes.todo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ToDOActivity extends AppCompatActivity {

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
        // itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        //lvItems.setAdapter(itemsAdapter);


        loadlist();
        setUpListViewListener();
    }


    private void setUpListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        items.remove(position);
                        itemsAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );
    }


    /**
     * void onClick(View view) {
     *
     * @SuppressWarnings("unchecked") ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
     * Comment comment = null;
     * switch (view.getId()) {
     * case R.id.add:
     * String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
     * int nextInt = new Random().nextInt(3);
     * // save the new comment to the database
     * comment = datasource.createComment(comments[nextInt]);
     * adapter.add(comment);
     * break;
     * case R.id.delete:
     * if (getListAdapter().getCount() > 0) {
     * comment = (Comment) getListAdapter().getItem(0);
     * datasource.deleteComment(comment);
     * adapter.remove(comment);
     * }
     * break;
     * }
     * adapter.notifyDataSetChanged();
     * }
     **/

    @Override
    protected void onResume() {
        super.onResume();
        loadlist();

        setUpListViewListener();
    }


    @Override
    protected void onPause() {

        super.onPause();
    }

    void loadlist() {
        List<BucketList> allbucketlists = BucketList.listAll(BucketList.class);
        listItem = new todoListAdapter(this, allbucketlists);
        lvItems.setEmptyView(emptyView);
        lvItems.setAdapter(listItem);

    }

    @OnClick(R.id.btn_add)
    void add() {
        Intent intent = new Intent(this, AddToDoActivity.class);
        startActivity(intent);
    }


}
