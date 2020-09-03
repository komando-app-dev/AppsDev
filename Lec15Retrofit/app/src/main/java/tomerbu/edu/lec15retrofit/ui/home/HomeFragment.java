package tomerbu.edu.lec15retrofit.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import tomerbu.edu.lec15retrofit.R;

//Settings...HTTP is blocked in android.
//Settings...Internet on the UI Thread in android.

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getMovies().observe(getViewLifecycleOwner(), (movies -> {
            textView.setText(movies.toString());
        }));
        return root;
    }
}