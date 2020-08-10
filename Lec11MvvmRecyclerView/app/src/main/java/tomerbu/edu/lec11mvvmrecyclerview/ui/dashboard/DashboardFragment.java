package tomerbu.edu.lec11mvvmrecyclerview.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import tomerbu.edu.lec11mvvmrecyclerview.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private TextView tvCounter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        tvCounter = root.findViewById(R.id.tvCounter);
        root.findViewById(R.id.btnIncrement).setOnClickListener(v -> dashboardViewModel.increment());
        root.findViewById(R.id.btnDecrement).setOnClickListener(v -> dashboardViewModel.decrement());

        // val->tvCounter.setText(val.toString())
        //להאזין לשינויים במודל:
        dashboardViewModel.getInt().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer val) {
                tvCounter.setText(val.toString());
            }
        });

        return root;
    }
}