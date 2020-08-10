package tomerbu.edu.lec11mvvmrecyclerview.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


//עבודה עם אובייקטים
public class DashboardViewModel extends ViewModel {

    private MutableLiveData<Integer> mInt;

    public DashboardViewModel() {
        mInt = new MutableLiveData<>();
        mInt.setValue(0);
    }

    public void increment(){
        //Integer value = mInt.getValue();
        if (mInt.getValue() == null)return;
        mInt.setValue(mInt.getValue() + 1);
    }

    public void decrement(){
        //Integer value = mInt.getValue();
        if (mInt.getValue() == null)return;
        mInt.setValue(mInt.getValue() - 1);
    }

    public LiveData<Integer> getInt() {
        return mInt;
    }
}