package proyecto.pidetucomida.ui.empresa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EmpresaViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public EmpresaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Empresa fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
