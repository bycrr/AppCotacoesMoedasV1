package br.com.bycrr.v1.appcotacoesmoedas.ui.coins;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CoinsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CoinsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
