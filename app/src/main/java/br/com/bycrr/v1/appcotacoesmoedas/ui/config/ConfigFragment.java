package br.com.bycrr.v1.appcotacoesmoedas.ui.config;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.util.SharedPrefManager;

public class ConfigFragment extends Fragment {

  private AdView adView;
  private ConfigViewModel configViewModel;
  SharedPrefManager sharedPrefManager;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    configViewModel =
      ViewModelProviders.of(this).get(ConfigViewModel.class);
    View root = inflater.inflate(R.layout.fragment_config, container, false);

    adView = root.findViewById(R.id.adView);
    AdRequest adRequest = new AdRequest.Builder().build();
    adView.loadAd(adRequest);

    /*final TextView textView = root.findViewById(R.id.text_home);
    configViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });*/
    sharedPrefManager = new SharedPrefManager();
    String direction = sharedPrefManager.readConfig("directAscDes", getContext());
    String showIconFlag = sharedPrefManager.readConfig("showIconFlag", getContext());
    String order = sharedPrefManager.readConfig("order", getContext());
    RadioButton directAsc = root.findViewById(R.id.radioAscending);
    RadioButton directDes = root.findViewById(R.id.radioDescending);
    RadioButton showIcon = root.findViewById(R.id.radioShowIconCoin);
    RadioButton showFlag = root.findViewById(R.id.radioShowFlagCoin);
    RadioButton orderCoin = root.findViewById(R.id.radioOrderCoin);
    RadioButton orderDate = root.findViewById(R.id.radioOrderDate);
    RadioButton orderValue = root.findViewById(R.id.radioOrderValue);

    switch (direction) {
      case "asc":
        directAsc.setChecked(true);
        directDes.setChecked(false);
        break;

      case "des":
        directAsc.setChecked(false);
        directDes.setChecked(true);
        break;

      default:
        directAsc.setChecked(false);
        directDes.setChecked(false);
        break;
    }
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

