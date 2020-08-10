package tomerbu.edu.lec11mvvmrecyclerview.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tomerbu.edu.lec11mvvmrecyclerview.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);


        View root = inflater.inflate(R.layout.fragment_notifications, container, false);


        RecyclerView rv = root.findViewById(R.id.recycler_notifications);


        notificationsViewModel.getTexts().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                //display as list (Linear Vertical)
                rv.setLayoutManager(new LinearLayoutManager(getContext()));//GridLayoutManager, Staggered
                //בפרגמנט יוצרים מופע של האדפטר ומשייכים אותו ל-recycler
                NotificationsAdapter adapter = new NotificationsAdapter(strings);
                rv.setAdapter(adapter);


            }
        });


        return root;
    }
}