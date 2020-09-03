package tomerbu.edu.lec18contentproviders;

import android.Manifest;
import android.content.ContentResolver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readContacts();

        //כדי להרשם לארועים ברמת מע ההפעלה - בדיוק אותו דבר כמו כל Broadcast אחר.
        registerReceiver(
                new MInternetReceiver(),
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        );
    }

    //Relative
        //Relative
        //LinearLayout
        //LinearLayout



    //dangerous protection level.

    //נשתמש מס' פעמים:
    private static final String permission = Manifest.permission.READ_CONTACTS;
    private static final int RC_CONTACTS = 0;

    private void readContacts() {
        //0)
        // נבדוק רשות - אם אין נבקש וreturn
        List<Contact> contacts = new ArrayList<>();
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{permission}, RC_CONTACTS);
            return;
        }
        //אם הגענו עד לשורה הזאת - יש רשות:

        //1)
        //מה הכתובת של ספק  התוכן: טבלה URI
        Uri uri = ContactsContract.Contacts.CONTENT_URI;//content://com.android.contacts/contacts

        //2)
        //חיבור לספק תוכן:
        //Context.getContentResolver -> אובייקט שמנהל את העבודה מול ספקי תוכן
        ContentResolver resolver = getContentResolver();

        //3)
        //NULLS -> SELECT * FROM Contacts
        Cursor cursor = resolver.query(uri, null, null, null, null);

        if (cursor == null) return;
        //4)
        //נקרא את המידע מהקורסור
        while (cursor.moveToNext()) {
            //יש 2 עמודות בטבלה הזאת - DISPLAY_NAME
            //ID
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            List<String> phones = getPhones(id);
            List<String> emails = getEmails(id);
            contacts.add(new Contact(name, Long.parseLong(id), phones, emails));
        }
        System.out.println(contacts);
        //בסוף המתודה. (5
        cursor.close();
    }

    //SELECT * FROM Phones WHERE id =
    private List<String> getPhones(String id) {
        List<String> phones = new ArrayList<>();

        //מה השלבים לעבודה עם ספק תוכן?
        //כתובת URI - טבלה
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        //projection:
        //אילו עמודות אנו רוצים במקום SELECT *
        // id, number
        String[] projection = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.NUMBER};

        //selection ~ Where in sql  //WHERE id = ?
        String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?"; //contact_id = ?

        //כל מה שנכנס בסימני השאלה ב-selection
        String[] selectionArgs = {id};//נחליף את סימן שאלה בID

        String orderBy = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " ASC"; //ID ASC

        Cursor cursor = getContentResolver().query(uri, projection, selection, selectionArgs, orderBy);
        //נחלץ את המידע מהcursor
        if (cursor == null) return phones;

        while (cursor.moveToNext()) {
            //בגלל שציינו projection
            // id, number
            String phone = cursor.getString(1); //cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));//
            phones.add(phone);
        }
        cursor.close();
        return phones;
    }

    private List<String> getEmails(String id) {
        List<String> emails = new ArrayList<>();

        //מה השלבים לעבודה עם ספק תוכן?
        //כתובת URI - טבלה
        Uri uri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;

        //projection:
        //אילו עמודות אנו רוצים במקום SELECT *
        // id, number
        String[] projection = {ContactsContract.CommonDataKinds.Email.CONTACT_ID, ContactsContract.CommonDataKinds.Email.ADDRESS};

        //selection ~ Where in sql  //WHERE id = ?
        String selection = ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=?"; //contact_id = ?

        //כל מה שנכנס בסימני השאלה ב-selection
        String[] selectionArgs = {id};//נחליף את סימן שאלה בID

        String orderBy = ContactsContract.CommonDataKinds.Email.CONTACT_ID + " ASC"; //ID ASC

        Cursor cursor = getContentResolver().query(uri, projection, selection, selectionArgs, orderBy);
        //נחלץ את המידע מהcursor
        if (cursor == null) return emails;

        while (cursor.moveToNext()) {
            //בגלל שציינו projection
            // id, address
            String address = cursor.getString(1); //cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));//
            emails.add(address);
        }
        cursor.close();
        return emails;
    }

}
//class Contact:
//name
//id
//List<String> phones
//List<String> emails
//ctor
//toString


//Content Providers:
// לקבל רשות. מניפסט.