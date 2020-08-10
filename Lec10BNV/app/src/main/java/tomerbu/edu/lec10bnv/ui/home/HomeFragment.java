package tomerbu.edu.lec10bnv.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import tomerbu.edu.lec10bnv.R;


//ViewController ( XML + JAVA)
public class HomeFragment extends Fragment {

    //1) create the view from XML Using Inflate.
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        //findView By Id
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText("Home Fragment!");
        return root;
    }
}