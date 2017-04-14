package com.vicki.mes.todo.Broadcasts;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.vicki.mes.todo.Models.BucketList;
import com.vicki.mes.todo.R;
import com.vicki.mes.todo.ui.ItemDetail;
import com.vicki.mes.todo.utils.AlarmService;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by kembo on 4/14/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    Long id;

    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        // get data via the key
         id = intent.getLongExtra("ITEM_ID",0);
        Uri alarmUri = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        // this will send a notification message
        if (id != null) {

            BucketList item = BucketList.findById(BucketList.class, id);
            // do something with the data
            sendNotification(item);

        }
        ///end


        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        intent.setComponent(comp);


    }
    public void sendNotification(BucketList item) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.todo_icon);
        Intent intent = new Intent(context, ItemDetail.class);
        intent.putExtra("ITEM_DETAIL_ID",id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                intent, 0);

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
        builder.setSubText("Tap to view activity info.");


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
    }


}
