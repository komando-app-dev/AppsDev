package tomerbu.edu.lec18contentproviders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FirstFragment extends Fragment {

    private FirstViewModel mViewModel;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.first_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // mViewModel = new ViewModelProvider(this).get(FirstViewModel.class);

        //change this line as to pass category:

        //1) take a parameter in Your Viewmodel class.
        //2) write your factory -> copy/paste from the example and change it according to your needs.
        //3) the line below:
        mViewModel = new ViewModelProvider(this,new FirstViewModelFactory(2)).get(FirstViewModel.class);
        // TODO: Use the ViewModel
    }

}