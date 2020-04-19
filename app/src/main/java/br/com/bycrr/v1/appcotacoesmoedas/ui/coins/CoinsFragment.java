package br.com.bycrr.v1.appcotacoesmoedas.ui.coins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;
import br.com.bycrr.v1.appcotacoesmoedas.util.SharedPrefManager;
import br.com.bycrr.v1.appcotacoesmoedas.util.Utility;

public class CoinsFragment extends Fragment {

  private AdView adView;
  private CoinsViewModel coinsViewModel;
  SharedPrefManager sharedPrefManager;
  String urlCoins;
  ArrayList<Coin> coinArrayList;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    coinsViewModel =
      ViewModelProviders.of(this).get(CoinsViewModel.class);
    View root = inflater.inflate(R.layout.fragment_coins, container, false);

    adView = root.findViewById(R.id.adView);
    AdRequest adRequest = new AdRequest.Builder().build();
    adView.loadAd(adRequest);

    sharedPrefManager = new SharedPrefManager();
    urlCoins = sharedPrefManager.readUrlCoins(getContext());
    coinArrayList = new ArrayList<>();
    if (urlCoins == null) urlCoins = Utility.URL_COINS;
    List<String> codeList = Utility.getCodeList(urlCoins);
    String code;
    Switch chkBox;

    for (String codeFull : codeList) {
      if (!codeFull.isEmpty()) {
        code = codeFull.substring(0, codeFull.indexOf("-"));

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

        } else if (code.equals("USDT")) {
          chkBox = root.findViewById(R.id.checkbox_usdt);
          chkBox.setChecked(true);

        } else if (code.equals("CAD")) {
          chkBox = root.findViewById(R.id.checkbox_cad);
          chkBox.setChecked(true);

        } else if (code.equals("AUD")) {
          chkBox = root.findViewById(R.id.checkbox_aud);
          chkBox.setChecked(true);

        } else if (code.equals("ARS")) {
          chkBox = root.findViewById(R.id.checkbox_ars);
          chkBox.setChecked(true);

        } else if (code.equals("JPY")) {
          chkBox = root.findViewById(R.id.checkbox_jpy);
          chkBox.setChecked(true);

        } else if (code.equals("CHF")) {
          chkBox = root.findViewById(R.id.checkbox_chf);
          chkBox.setChecked(true);

        } else if (code.equals("CNY")) {
          chkBox = root.findViewById(R.id.checkbox_cny);
          chkBox.setChecked(true);

        /*} else if (code.equals("YLS")) {
          chkBox = root.findViewById(R.id.checkbox_yls);
          chkBox.setChecked(true);*/

        } else if (code.equals("LTC")) {
          chkBox = root.findViewById(R.id.checkbox_ltc);
          chkBox.setChecked(true);

        } else if (code.equals("ETH")) {
          chkBox = root.findViewById(R.id.checkbox_eth);
          chkBox.setChecked(true);

        } else if (code.equals("XRP")) {
          chkBox = root.findViewById(R.id.checkbox_xrp);
          chkBox.setChecked(true);
        }
      }
    }
    return root;
  }
}
