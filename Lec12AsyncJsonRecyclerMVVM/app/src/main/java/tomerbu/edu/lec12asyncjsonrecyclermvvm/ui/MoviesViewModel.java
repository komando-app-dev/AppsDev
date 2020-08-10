package tomerbu.edu.lec12asyncjsonrecyclermvvm.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import tomerbu.edu.lec12asyncjsonrecyclermvvm.models.Movie;
import tomerbu.edu.lec12asyncjsonrecyclermvvm.models.MovieDataSource;

//מייצג תמונת אמת של הרשימה באפליקציה - כולל האייטם שעליו לחצו, כולל שגיאה:
public class MoviesViewModel extends ViewModel {

    //hold livedata for Movies:
    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

    //TODO: 1)מגדירים אירוע של הסרט שנבחר
    //Observable<Movie> //A) post changes, B) listen to changes.
    private MutableLiveData<Movie> selectedMovie = new MutableLiveData<>();

    //No args constructor!
    public MoviesViewModel() {

        new MovieDataSource().fetchMovies((movies, e) -> {
            if (e != null) {
                e.printStackTrace();
                //TODO: how to report error?
            } else {
                this.movies.postValue(movies);
            }
        });
    }
    //Master detail: News Api

    //expose the live data as Read only
    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    //expose the selected item as mutable so that the adapter may change it's value.
    public MutableLiveData<Movie> getSelectedMovie() {
        return selectedMovie;
    }
}