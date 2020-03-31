package br.com.bycrr.v1.appcotacoesmoedas.ui.coins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;
import br.com.bycrr.v1.appcotacoesmoedas.util.SharedPrefManager;
import br.com.bycrr.v1.appcotacoesmoedas.util.Utility;

public class CoinsFragment extends Fragment {

  private CoinsViewModel coinsViewModel;
  SharedPrefManager sharedPrefManager;
  String urlCoins;
  ArrayList<Coin> coinArrayList;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    coinsViewModel =
      ViewModelProviders.of(this).get(CoinsViewModel.class);
    View root = inflater.inflate(R.layout.fragment_coins, container, false);
    sharedPrefManager = new SharedPrefManager();
    urlCoins = sharedPrefManager.readUrlCoins(getContext());
    coinArrayList = new ArrayList<>();
    if (urlCoins == null) urlCoins = Utility.URL_COINS;
    List<String> codeList = Utility.getCodeList(urlCoins);
    String code;
    CheckBox chkBox;

    for (String codeFull : codeList) {
      if (!codeFull.isEmpty()) {
        code = codeFull.substring(0, 3);

        if (code.equals("BTC")) {
          chkBox = root.findViewById(R.id.checkbox_btc);
          chkBox.setChecked(true);

        } else if (code.equals("USD")) {
          chkBox = root.findViewById(R.id.checkbox_usd);
          chkBox.setChecked(true);

        } else if (code.equals("EUR")) {
          chkBox = root.findViewById(R.id.checkbox_eur);
          chkBox.setChecked(true);

        } else if (code.equals("GBP")) {
          chkBox = root.findViewById(R.id.checkbox_gbp);
          chkBox.setChecked(true);
        }
      }
    }
    return root;
  }
}
