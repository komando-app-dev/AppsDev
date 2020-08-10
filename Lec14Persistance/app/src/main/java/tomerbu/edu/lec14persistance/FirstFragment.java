package tomerbu.edu.lec14persistance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText etNote = view.findViewById(R.id.etNote);
        getNote(etNote);

        //etNote.setFilters(); Length, Chars,

        //listener:
        etNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                System.out.println("S:" + s);
                System.out.println("start:" + start);
                // etNote.setText('A')
                // a המשתמש כבר הקליד
                //אבל עדיין לא רואים את a על המסך
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //index, count,
                // a המשתמש כבר הקליד
                //כבר רואים את a על המסך - אבל עדיין יש לנו הזדמנות להגיב.
                //System.out.println("s" + s);

                saveNote(etNote.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //השינוי בוצע בהצלחה
            }
        });
    }


    //שיעורי בית: צרו אפליקצית פתקים שמתשמשת רק בSharedPreferences
    //הפתק הבא - טקסט - הפתק הקודם
    private String getNote(EditText etNote){
        FragmentActivity activity = getActivity();
        if (activity == null)return ""; //התגוננות מפני מקרים שאין אקטיביטי

        //גישה לאובייקט של SharedPreferences
        SharedPreferences prefs = activity.getSharedPreferences("Note1", Context.MODE_PRIVATE);
        String text = prefs.getString("note1", "");

        etNote.setText(text);

        return text;
    }

    private void saveNote(String text){
        //1) SharedPreferences.

        FragmentActivity activity = getActivity();
        if (activity == null)return; //התגוננות מפני מקרים שאין אקטיביטי

        //PRIVATE -> רק האפליקציה שלנו יכולה לקרוא ולכתוב לקובץ

        //קובץ שאני לא שולט בשם שלו - וזה קובץ שמיועד רק לאקטיביטי הנוכחי.
        //לכל אקטיביטי - יש קובץ משלו:
       // SharedPreferences aprefs = activity.getPreferences(Context.MODE_PRIVATE);

        //קובץ שאנחנו בוחרים את השם של הקובץ - ויכול להיות משותף לכל האפליקציה
        //יצירה של קובץ בשם Note1 אם הוא לא קיים
        SharedPreferences prefs = activity.getSharedPreferences("Note1", Context.MODE_PRIVATE);


        //אובייקט שמאפשר עריכה - כתיבה.
        SharedPreferences.Editor editor = prefs.edit();

        //key values: (Map)
        editor.putString("note1", text);//3 times

        //editor.putStringSet("names", new HashSet<String>());

        //ArrayList<String> names = new ArrayList<>();//Moshe, Avi, Moshe
        //convert array list to set?
        //HashSet<String> namesSet = new HashSet<>(names);//Avi, Moshe

        //editor.putInt("score", 10);
        // בסוף התהליך נכתוב את המידע לקובץ
        editor.apply();//כתיבה אסינכרונית - יוצר תהליכון שייכתוב

        //getActivity().getSharedPreferences("Note1", Context.MODE_PRIVATE).edit().putString("Hi", "mm").apply();
    }
}
//האם כבר ראה את App Tutorial?
//email?
//notifications true/false
//userName?
//האם יש משתמש מחובר?



//Json Web Tokens - אבטוח של גישה לצד שרת




//        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });