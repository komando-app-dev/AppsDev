package tomerbu.edu.lec10navigationandfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class ThirdFragment extends Fragment {
    //1) create the view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //the view was created:
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    //2) on view created:
    //events + findView By id!
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //view.findViewById.

        //Context?

        //re-write the problematic line:
        //        //Play (Run)
        //        //build->clean project
        //        //Close android studio and reopen.
        //        //File invalidate caches and restart (לוקח מלא זמן)


        //fragment - > does not extend context
        //Context context = getContext();
        //FragmentActivity activity = getActivity();
        //findNavController

       // NavController navController = NavHostFragment.findNavController(this);

        //
        view.findViewById(R.id.btnGoHome).setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_thirdFragment_to_FirstFragment));
        //Toast.makeText(getContext(), "Hey", Toast.LENGTH_SHORT).show();

    }
}