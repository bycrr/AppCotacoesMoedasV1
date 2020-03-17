package br.com.bycrr.v1.appcotacoesmoedas.ui.coins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import br.com.bycrr.v1.appcotacoesmoedas.R;

public class CoinsFragment extends Fragment {

    private CoinsViewModel coinsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        coinsViewModel =
                ViewModelProviders.of(this).get(CoinsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_config, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        coinsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
