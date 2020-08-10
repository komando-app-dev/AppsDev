package tomerbu.edu.lec10bnv.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import tomerbu.edu.lec10bnv.R;

public class NotificationsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //View instance from xml
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        //view.findViewById
        TextView textView = root.findViewById(R.id.text_notifications);

        textView.setText("Notifications!");
        return root;
    }
}
