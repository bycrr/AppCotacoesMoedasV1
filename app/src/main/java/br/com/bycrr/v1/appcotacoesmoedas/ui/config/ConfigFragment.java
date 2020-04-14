package br.com.bycrr.v1.appcotacoesmoedas.ui.config;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.util.SharedPrefManager;

public class ConfigFragment extends Fragment {

  private ConfigViewModel configViewModel;
  SharedPrefManager sharedPrefManager;

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
    sharedPrefManager = new SharedPrefManager();
    String showIconFlag = sharedPrefManager.readConfig("showIconFlag", getContext());
    String order = sharedPrefManager.readConfig("order", getContext());
    RadioButton showIcon = root.findViewById(R.id.radioShowIconCoin);
    RadioButton showFlag = root.findViewById(R.id.radioShowFlagCoin);
    RadioButton orderCoin = root.findViewById(R.id.radioOrderCoin);
    RadioButton orderDate = root.findViewById(R.id.radioOrderDate);
    RadioButton orderValue = root.findViewById(R.id.radioOrderValue);

    switch (showIconFlag) {
      case "icon":
        showIcon.setChecked(true);
        showFlag.setChecked(false);
        break;

      case "flag":
        showIcon.setChecked(false);
        showFlag.setChecked(true);
        break;

      default:
        showIcon.setChecked(false);
        showFlag.setChecked(false);
        break;
    }
    switch (order) {
      case "coin":
        orderCoin.setChecked(true);
        orderDate.setChecked(false);
        orderValue.setChecked(false);
        break;

      case "date":
        orderCoin.setChecked(false);
        orderDate.setChecked(true);
        orderValue.setChecked(false);
        break;

      case "value":
        orderCoin.setChecked(false);
        orderDate.setChecked(false);
        orderValue.setChecked(true);
        break;

      default:
        orderCoin.setChecked(false);
        orderDate.setChecked(false);
        orderValue.setChecked(false);
        break;
    }
    return root;
  }
}

