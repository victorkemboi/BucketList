package com.vicki.mes.todo.ui;

/**
 * Created by kembo on 4/11/2017.
 */


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vicki.mes.todo.App;
import com.vicki.mes.todo.Models.BucketList;
import com.vicki.mes.todo.R;

public class OptionsFragment extends DialogFragment {

    BucketList optionItem;



    public OptionsFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }
    public static interface OnCompleteListener {
        public abstract void onComplete(int option);
    }

    private OnCompleteListener mListener;

    // make sure the Activity implemented it
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteListener)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }

    public static OptionsFragment newInstance() {
       OptionsFragment frag = new OptionsFragment();
       /*  Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args); */
        return frag;

    }

 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     App app = App.getInstance();
     optionItem = app.selectedBucketList;
     final View view = inflater.inflate(R.layout.options_fragment, container);
     Button delete=(Button)view.findViewById(R.id.btn_delete);
     delete.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
             // TODO Auto-generated method stub
             int option = 1;
             App app = App.getInstance();
             app.selectedBucketList = optionItem;
             mListener.onComplete(option);
             dismiss();


         }
     });
     return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get field from view

    }




}