package proyecto.pidetucomida.ui.desarrolladores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import proyecto.pidetucomida.R;


public class DesarrolladoresFragment extends Fragment {
    private DesarrolladoresViewModel desarrolladoresViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        desarrolladoresViewModel =
                ViewModelProviders.of(this).get(DesarrolladoresViewModel.class);
        View root = inflater.inflate(R.layout.fragment_desarrolladores, container, false);
        //final TextView textView = root.findViewById(R.id.text_View19);
        desarrolladoresViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });
        return root;
    }
}
