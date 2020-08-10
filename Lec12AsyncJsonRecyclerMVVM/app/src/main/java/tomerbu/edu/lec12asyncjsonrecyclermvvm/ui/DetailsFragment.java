package tomerbu.edu.lec12asyncjsonrecyclermvvm.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import tomerbu.edu.lec12asyncjsonrecyclermvvm.R;
import tomerbu.edu.lec12asyncjsonrecyclermvvm.models.Movie;

public class DetailsFragment extends Fragment {

    private DetailsViewModel mViewModel;

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        // TODO: Use the ViewModel

        Bundle args = getArguments();
        if (args == null) return;
        Movie m = args.getParcelable("movie");
        if (m == null) { return; }

        Toast.makeText(getContext(), m.getTitle(), Toast.LENGTH_SHORT).show();

        //find view by id:
    }

}