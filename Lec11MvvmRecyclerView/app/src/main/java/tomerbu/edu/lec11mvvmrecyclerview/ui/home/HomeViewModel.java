package tomerbu.edu.lec11mvvmrecyclerview.ui.home;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    CountDownTimer cdt;
    //Handler h = new Handler();
    //properties:
    //מידע מהמודל אחרי עיבוד.
    private MutableLiveData<String> mText
            = new MutableLiveData<>();

    //expose the read only Version
    public LiveData<String> getmText() {
        return mText;
    }


    //public MutableLiveData<Integer> mInt = new MutableLiveData<>();
    //Person!

    //Live Data<T> - Wrapper<T>
    //לתת callback בכל פעם שהמידע השתנה
    //בנאי:
    public HomeViewModel() {
        cdt = new CountDownTimer(30_000, 1_000) {
            @Override
            public void onTick(long l) {
                //get the stock price from JSON
                mText.setValue("Hello " + l);
            }

            @Override
            public void onFinish() {
                mText.setValue("Time Up");
            }
        };
        cdt.start();

        //mText.setValue("This is Wow fragment");
    }

    //deleted the getter.
}