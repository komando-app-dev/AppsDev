package tomerbu.edu.lec11mvvmrecyclerview.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<List<String>> mTexts;

    public NotificationsViewModel() {
        mTexts = new MutableLiveData<>();

        //TODO: get the data from a database
        ArrayList<String> list = new ArrayList<>(Arrays.asList(
                "Moshe", "Dave", "Sacha", "Avi", "Ron", "Anna",
                "Moshe", "Dave", "Sacha", "Avi", "Ron", "Anna",
                "Moshe", "Dave", "Sacha", "Avi", "Ron", "Anna"
        ));

        //wrap the list in a live data object:
        mTexts.setValue(list);
    }

    public LiveData<List<String>> getTexts() {
        return mTexts;
    }
}