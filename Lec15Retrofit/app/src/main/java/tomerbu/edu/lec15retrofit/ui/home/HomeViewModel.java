package tomerbu.edu.lec15retrofit.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import tomerbu.edu.lec15retrofit.movies.TMDBDataSource;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");


        TMDBDataSource.getInstance().getMovies();
    }

    public LiveData<String> getText() {
        return mText;
    }
}