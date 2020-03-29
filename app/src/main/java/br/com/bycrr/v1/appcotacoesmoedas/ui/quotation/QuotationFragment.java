package br.com.bycrr.v1.appcotacoesmoedas.ui.quotation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.adapter.QuotationListAdapter;
import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;
import br.com.bycrr.v1.appcotacoesmoedas.util.SharedPrefManager;
import br.com.bycrr.v1.appcotacoesmoedas.util.Utility;

public class QuotationFragment extends Fragment {

  private QuotationViewModel quotationViewModel;
  ListView listView;
  //CoinController coinController;
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
    //GetOnlineQuotations task = new GetOnlineQuotations();
    //task.execute();
    sharedPrefManager = new SharedPrefManager();
    coinArrayList = new ArrayList<>();
    urlCoins = sharedPrefManager.readUrlCoins(getContext());
    if (urlCoins == null) urlCoins = Utility.URL_COINS;
    List<String> codeList = Utility.getCodeList(urlCoins);

    for (String code: codeList) {
      coinArrayList.add(sharedPrefManager.readCoin(code.substring(0,3), getContext()));
    }

    // TODO: mudar p/funcionar c/loader...  https://www.devmedia.com.br/consumindo-dados-de-um-web-service-com-android/33717
    // TODO: analisar estas soluções => https://pt.stackoverflow.com/questions/38170/como-obter-resultado-de-uma-tarefa-ass%C3%ADncrona-no-android
    final QuotationListAdapter adapter = new QuotationListAdapter(coinArrayList, getContext());
    listView.setAdapter(adapter);
    return view;
  }

  private ArrayList<Coin> mockFakeData() {
    ArrayList<Coin> coinArrayList = new ArrayList<>();
    Date data = new Date(System.currentTimeMillis());

    Coin coin = new Coin();
    coin.setTitle("BitCoin");
    coin.setSymbol("B$ 1 = R$ ");
    //coin.setDateTime("22/03/2020 16:30h");
    coin.setDateTime(data.toString());
    coin.setValueBid(BigDecimal.valueOf(10d));
    coinArrayList.add(coin);

    coin = new Coin();
    coin.setTitle("Dollar");
    coin.setSymbol("US$ 1 = R$ ");
    //coin.setDateTime("22/03/2020 16:30h");
    coin.setDateTime(data.toString());
    coin.setValueBid(BigDecimal.valueOf(10d));
    coinArrayList.add(coin);

    return coinArrayList;
  }
}
