package tomerbu.edu.lec15retrofit.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import tomerbu.edu.lec15retrofit.movies.Movie;
import tomerbu.edu.lec15retrofit.movies.MoviesRepo;


public class HomeViewModel extends AndroidViewModel {
    //properties:
    //data that we pass to the fragment:
    private MutableLiveData<List<Movie>> mMovies = new MutableLiveData<>();
    private MutableLiveData<Exception> mException = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);

        //context with mvvm
        MoviesRepo.getInstance(getApplication()).getMovies((movies, exc) -> {
            if (movies != null){
                mMovies.postValue(movies);
            }else {
                mException.postValue(exc);
            }
        });
    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }

    public LiveData<Exception> getException() {
        return mException;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        //clear resources
    }

    //onPause of the fragment
}