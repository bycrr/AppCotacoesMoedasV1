package br.com.bycrr.v1.appcotacoesmoedas.ui.quotation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.adapter.QuotationListAdapter;
import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;
import br.com.bycrr.v1.appcotacoesmoedas.util.SharedPrefManager;
import br.com.bycrr.v1.appcotacoesmoedas.util.Utility;

public class QuotationFragment extends Fragment {

  private QuotationViewModel quotationViewModel;
  ListView listView;
  View view;
  ArrayList<Coin> coinArrayList;
  SharedPrefManager sharedPrefManager;
  String urlCoins;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    quotationViewModel =
      ViewModelProviders.of(this).get(QuotationViewModel.class);
    view = inflater.inflate(R.layout.fragment_quotation, container, false);
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
    final QuotationListAdapter adapter = new QuotationListAdapter(coinArrayList, getContext());
    listView.setAdapter(adapter);
    return view;
  }
}
