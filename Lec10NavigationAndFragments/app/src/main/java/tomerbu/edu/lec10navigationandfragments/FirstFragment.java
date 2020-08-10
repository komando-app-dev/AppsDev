package tomerbu.edu.lec10navigationandfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


//ViewController: FirstFragment.Java + fragment_first.xml

//Fragment - גירסה יעילה וקלת משקל של ViewController
//  לא יורש מCONTEXT
//  אין findViewById
public class FirstFragment extends Fragment {

    //properties:

    //איך פרגמנט מופיע? הוא מוצג באקטיביטי

    //1) בוחרים קובץ עיצוב ויוצרים ממנו View
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //setContentView(R.layout.activity_main);

        //כאן קובעים ויוצרים את העיצוב של הפרגמנט
        // Inflate the layout for this fragment

        //View is an Object that can draw to the screen. Respond to touch events.


        //we have an xml file -> R.layout.fragment_first

        //הפעולה מחזירה View
        // יש לנו קובץ עיצוב בסיומת XML


        //fragment_first, parent(מה הגודל)
        //false - לא להכניס לקונטיינר - רק לפי המידות של הקונטיינר
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    //  2)  הView מוכן. עכשיו מותר findViewByID ואירועים
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       view.findViewById(R.id.button_first).setOnClickListener(v->{
           //3)get a ref to the NavigationController
           NavController navController = NavHostFragment.findNavController(this);

           navController.navigate(R.id.action_FirstFragment_to_SecondFragment);
       });






       //אחרי הפסקה - ניצור פרגמנט נוסף, נוסיף אותו לגרף, נוסיף action - נלמד על המרכיבים של הגרף



//        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
        //TODO: Question.
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }

    //life cycle: on Activity attached, on Activity detached
}