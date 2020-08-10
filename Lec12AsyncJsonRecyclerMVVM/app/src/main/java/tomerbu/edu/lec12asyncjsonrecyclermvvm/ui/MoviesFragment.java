package tomerbu.edu.lec12asyncjsonrecyclermvvm.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import tomerbu.edu.lec12asyncjsonrecyclermvvm.R;
import tomerbu.edu.lec12asyncjsonrecyclermvvm.models.Movie;

public class MoviesFragment extends Fragment {

    private MoviesViewModel mViewModel;

    //Fragments AND Activities AND ViewModels Must have a no Args constructor (super)
    //factory method: oop constructor replacement

    // public static MoviesFragment newInstance() {
    //     return new MoviesFragment();
    // }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movies_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        if (getView() == null) {
            return;
        }

        RecyclerView rvMovies = getView().findViewById(R.id.rvMovies);

        //get the live data from the view model:
        MutableLiveData<Movie> mSelectedMovie = mViewModel.getSelectedMovie();

        mViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
            //adapter adapts List<movie> to recycler:
            //pass the live data to the adapter:
            //TODO:4) pass the event to the adapter:
            MovieAdapter adapter = new MovieAdapter(movies, mSelectedMovie);

            //assign the adapter to the recycler:
            rvMovies.setAdapter(adapter);

            //grid, table(Linear),
            rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        });

        //TODO: 5) listen to adapter reports about clicks:
        //ברגע שיש סרט נבחר - מיד עוברים אליו:
        mSelectedMovie.observe(getViewLifecycleOwner(), movie->{
            if (movie == null){return;}
            //respond to clicks:
            //Navigation code:
            NavController navController = NavHostFragment.findNavController(this);

            //new bundle for passing data from Master to Details
            Bundle args = new Bundle();
            args.putParcelable("movie", movie);//TODO: Make movie parcelable.

            navController.navigate(R.id.action_moviesFragment_to_detailsFragment, args);
            //clear the selected movie:
            mSelectedMovie.setValue(null);
        });
    }
}

//Fragment Lifecycle