package br.com.bycrr.v1.appcotacoesmoedas.ui.quotation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuotationViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QuotationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
