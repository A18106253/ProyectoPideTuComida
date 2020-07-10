package proyecto.pidetucomida.ui.platos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlatosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlatosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is platos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}