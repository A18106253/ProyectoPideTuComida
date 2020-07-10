package proyecto.pidetucomida.ui.bebidas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BebidasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BebidasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is bebidas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}