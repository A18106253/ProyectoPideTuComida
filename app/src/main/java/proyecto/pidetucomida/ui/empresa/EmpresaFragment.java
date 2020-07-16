package proyecto.pidetucomida.ui.empresa;

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
public class EmpresaFragment extends Fragment {

    private EmpresaViewModel empresaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        empresaViewModel=
                ViewModelProviders.of(this).get(EmpresaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_empresa, container, false);
        final TextView textView = root.findViewById(R.id.text_empresa);
        empresaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}