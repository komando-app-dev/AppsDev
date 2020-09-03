package tomerbu.edu.lec18contentproviders;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * נרצה להעביר את הקוד למקום מסודר SRP
 * נזכור שהמטרה של VM היא להוריד עבודה מהFragment
 */
public class FirstViewModelFactory implements ViewModelProvider.Factory {
    //property for the parameter:
    private int category;

    //constructor:
    public FirstViewModelFactory(int category) {
        this.category = category;
    }


    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //instance of -> is the casting safe.
        if (FirstViewModel.class.isAssignableFrom(modelClass)) {
            return (T) new FirstViewModel(category);
        }else {
            throw new IllegalArgumentException("Wrong view model class.");
        }
    }
}
