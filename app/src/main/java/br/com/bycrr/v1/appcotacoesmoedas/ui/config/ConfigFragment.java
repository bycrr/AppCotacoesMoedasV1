package br.com.bycrr.v1.appcotacoesmoedas.ui.config;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import br.com.bycrr.v1.appcotacoesmoedas.R;

public class ConfigFragment extends Fragment {

  private ConfigViewModel configViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    configViewModel =
      ViewModelProviders.of(this).get(ConfigViewModel.class);
    View root = inflater.inflate(R.layout.fragment_config, container, false);
    /*final TextView textView = root.findViewById(R.id.text_home);
    configViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });*/
    return root;
  }
}

