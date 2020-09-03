package tomerbu.edu.lec18contentproviders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MInternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Internet Changed");
        System.out.println(intent);

        //Intent intent1 = new Intent(context, );
        //context.startService(intent1);
    }
}

//האם יכולנו ליצור מחלקה אנונימית? כן.
//אנחנו מעדיפים להפריד
//דגש על כך שBroadcastReceiver הוא קומפומננטה באנדרואיד.

//קומפוננטות שמהן מורכבת אנדרואיד.
//Activity, Service, Intent, Intent-Filter, BroadcastReceiver, Application, Content-Provider