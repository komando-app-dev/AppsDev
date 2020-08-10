package tomerbu.edu.lec14persistance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        //Singleton -> since it takes a lot of time and energy to init this class...
        //if we want the database everywhere in the app.

//        //fileName.sqlite
//        TodosDatabase db = Room.databaseBuilder(getContext(),
//                TodosDatabase.class, "filename").build();


        TodosDatabase db = TodosRepo.getSharedInstance(getContext()).db;

        new Thread(() -> {
            db.getTodosDao().add(new Todo("Call Mom", "Important"));

            List<Todo> all = db.getTodosDao().getAll();
        }).start();


    }
}

//save Movies from json to your database -> ...
//TodosRepo Json(if download is successful -> show json)
//             Network->catch

//  my data base:
//             Groupon once a day
//             Wheather, hourly, weekly

// caching headers -> keep-alive (12 hours)

// if no internet (exception) -> DB
//SharedPreferences -> String  -> new Date()


//BLOB -> binary large object