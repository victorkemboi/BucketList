package com.vicki.mes.todo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vicki.mes.todo.Models.BucketList;
import com.vicki.mes.todo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kemboi on 4/2/17.
 */

public class todoListAdapter extends BaseListAdapter<BucketList> {


    public todoListAdapter(Context context, List<BucketList> BucketList) {
        super(context);
        setItems(BucketList);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        todoListAdapter.ViewHolder viewHolder;

        if (convertView == null) {

            // inflate the layout
            LayoutInflater inflate = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.todo_list_item, null);

            // well set up the ViewHolder
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (todoListAdapter.ViewHolder) convertView.getTag();

        }
        //Update ViewHolder with data.
        BucketList a = getItem(position);
        viewHolder.itemTitle.setText(a.getTitle());
        viewHolder.itemDescription.setText(a.getDescription());
        viewHolder.itemDate.setText(a.getDate());
        viewHolder.itemTime.setText(String.format("%s hrs",a.getTime()));
        viewHolder.itemCategory.setText(a.getCategory());

        if(a.getCategory().equals("Alarm")){
            viewHolder.type.setImageResource(R.drawable.alarm);
        }else if(a.getCategory().equals("Completed")){
            viewHolder.type.setImageResource(R.drawable.completed);
        }else if(a.getCategory().equals("Bucket List")){
            viewHolder.type.setImageResource(R.drawable.bucket);
        }else if(a.getCategory().equals("Meeting")){
            viewHolder.type.setImageResource(R.drawable.meeting);
        }else if(a.getCategory().equals("Other")){
            viewHolder.type.setImageResource(R.drawable.other);
        }


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_item_title)
        TextView itemTitle;

        @BindView(R.id.tv_item_description)
        TextView itemDescription;

        @BindView(R.id.tv_item_date)
        TextView itemDate;
        @BindView(R.id.tv_item_time)
        TextView itemTime;
        @BindView(R.id.tv_item_category)
        TextView itemCategory;
        @BindView(R.id.iv_type)
        ImageView type;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }


    }


}
