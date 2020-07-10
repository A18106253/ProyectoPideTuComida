package proyecto.pidetucomida.ui.platos;

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

public class PlatosFragment extends Fragment {

    private PlatosViewModel platosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        platosViewModel =
                ViewModelProviders.of(this).get(PlatosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_platos, container, false);
        final TextView textView = root.findViewById(R.id.text_platos);
        platosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}