package tomerbu.edu.lec18contentproviders;

import androidx.lifecycle.ViewModel;

public class FirstViewModel extends ViewModel {

    //datum that we need from the fragment.
    private int category;


    //constructor
    public FirstViewModel(int category) {
        this.category = category;
    }

    //no default constructor.
}