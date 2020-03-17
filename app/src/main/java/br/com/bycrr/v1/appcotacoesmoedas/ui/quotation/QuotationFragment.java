package br.com.bycrr.v1.appcotacoesmoedas.ui.quotation;

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

import br.com.bycrr.v1.appcotacoesmoedas.R;

public class QuotationFragment extends Fragment {

    private QuotationViewModel quotationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        quotationViewModel =
                ViewModelProviders.of(this).get(QuotationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_quotation, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        quotationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
