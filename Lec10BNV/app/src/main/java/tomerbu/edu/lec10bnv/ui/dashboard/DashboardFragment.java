package tomerbu.edu.lec10bnv.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import tomerbu.edu.lec10bnv.R;

public class DashboardFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //View instance from xml
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //view.findViewById
        TextView textView = root.findViewById(R.id.text_dashboard);

        textView.setText("Dash Board!");
        return root;
    }
}

//MVVM: