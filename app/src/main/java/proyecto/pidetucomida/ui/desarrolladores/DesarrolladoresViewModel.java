package proyecto.pidetucomida.ui.desarrolladores;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DesarrolladoresViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public DesarrolladoresViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is desarrolladores fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
