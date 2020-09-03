package tomerbu.edu.lec18contentproviders;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */

//1) extends receiver:
public class NewAppWidget extends AppWidgetProvider {


    //עדכון ממשק משתמש ותגובה לאירועים:3
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        //...Json -> Weather data -> Intent update

        //טקסט שנרצה להציג
        CharSequence widgetText = new Date().toString();


        //appWidgetManager -> update my widget: update a view with id:

        // Construct the RemoteViews object (WITH your xml layout file)
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);


        //עדכוני UI:
        //textView.setText-> setTextViewText
        views.setTextViewText(R.id.appwidget_text, widgetText);
        //setOn**listener:

        //איך נרענן את הווידגט?
        //נזכור שWidget הוא BroadcastReceiver
        //נרצה לשלוח "שידור" Broadcast - ה"שידור" מרענן את הWidget

        //1) ניצור Intent שתפקידו לשלוח Broadcast אינטנט מפורש
        Intent intent = new Intent(context, NewAppWidget.class);

        //2) נקבע את Action של הIntent ברירת מחדל - הווידג'ט מאזין לAction הזה
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        // נוסיף את הID של הווידגט שאנו רוצים לעדכן (3
        int[] idArray = {appWidgetId};
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);


        //wrap the regular intent in a "Pending intent"
        //מערכת ההפעלה שומרת את הIntent שלנו לשימוש בלחיצה.
        //אם אנדרואיד רואה שכבר שלחנו את הIntent הזה בעבר - היא מתעלמת ממנו
        //
        PendingIntent pi = PendingIntent.getBroadcast(context, appWidgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.btnUpdate, pi);//context->db, files,activity


        //apply/commit
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    //2)פעולה שמופעלת לפי האינטרוול או אם שלחנו Intent
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        //בגלל שניתן לכלול את אותו יישומון-קטן מספר פעמים יש לולאה
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        System.out.println("Widget enabled");
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        System.out.println("Widget disabled");
    }


//    //new Intent Filter("Address")
//    BroadcastReceiver mRecevier = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//        }
//    };
}

