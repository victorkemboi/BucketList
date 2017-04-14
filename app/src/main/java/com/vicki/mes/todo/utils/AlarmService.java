package com.vicki.mes.todo.utils;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;

/**
 * Created by kembo on 4/14/2017.
 */

public class AlarmService extends IntentService {
    private NotificationManager alarmNotificationManager;

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {

        alarmNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        alarmNotificationManager.cancel(intent.getIntExtra("notificationId",-1));



    }


}