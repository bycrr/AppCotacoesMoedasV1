package br.com.bycrr.v1.appcotacoesmoedas.ui.quotation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.adapter.QuotationListAdapter;
import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;
import br.com.bycrr.v1.appcotacoesmoedas.util.SharedPrefManager;
import br.com.bycrr.v1.appcotacoesmoedas.util.Utility;

public class QuotationFragment extends Fragment {

  private AdView adView;
  private QuotationViewModel quotationViewModel;
  ListView listView;
  View view;
  ArrayList<Coin> coinArrayList;
  SharedPrefManager sharedPrefManager;
  String urlCoins, showIconFlag, order, direction;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    quotationViewModel =
      ViewModelProviders.of(this).get(QuotationViewModel.class);
    view = inflater.inflate(R.layout.fragment_quotation, container, false);

    adView = view.findViewById(R.id.adView);
    AdRequest adRequest = new AdRequest.Builder().build();
    adView.loadAd(adRequest);

    listView = view.findViewById(R.id.listview);
    sharedPrefManager = new SharedPrefManager();
    coinArrayList = new ArrayList<>();
    urlCoins = sharedPrefManager.readUrlCoins(getContext());
    if (urlCoins == null) urlCoins = Utility.URL_COINS;
    List<String> codeList = Utility.getCodeList(urlCoins);

    for (String code: codeList) {
      if (!code.isEmpty()) {
        coinArrayList.add(sharedPrefManager.readCoin(code.substring(0, code.indexOf("-")), getContext()));
      }
    }
    showIconFlag = sharedPrefManager.readConfig("showIconFlag", getContext());
    order = sharedPrefManager.readConfig("order", getContext());
    direction = sharedPrefManager.readConfig("directAscDes", getContext());
    Collections.sort(coinArrayList, new ComparatorCoins(order, direction));

    final QuotationListAdapter adapter = new QuotationListAdapter(coinArrayList, showIconFlag, getContext());
    listView.setAdapter(adapter);
    return view;
  }

  class ComparatorCoins implements Comparator<Coin> {
    String order, direction;
    public ComparatorCoins(String order, String direction) {
      this.order = order;
      this.direction = direction;
    }
    public int compare(Coin coin1, Coin coin2) {

      switch (order) {
        case "value":
          if (coin1.getValueBid().doubleValue() < coin2.getValueBid().doubleValue())
            return (direction.equals("asc") ? -1 : +1);
          else
            return (direction.equals("asc") ? +1 : -1);

        case "date":
          if (coin1.getDateTime().compareTo(coin2.getDateTime()) < 0)
            return (direction.equals("asc") ? -1 : +1);
          else
            return (direction.equals("asc") ? +1 : -1);

        default:
          if (coin1.getTitle().compareTo(coin2.getTitle()) < 0)
            return (direction.equals("asc") ? -1 : +1);
          else
            return (direction.equals("asc") ? +1 : -1);
      }
    }
  }
}
