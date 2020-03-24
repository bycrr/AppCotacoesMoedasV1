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

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.adapter.QuotationListAdapter;
import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;
import br.com.bycrr.v1.appcotacoesmoedas.util.GetOnlineQuotations;

public class QuotationFragment extends Fragment {

  private QuotationViewModel quotationViewModel;
  ListView listView;
  //CoinController coinController;
  View view;
  ArrayList<Coin> coinArrayList;
  //static ListCoin listcoin;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /*public QuotationFragment(ArrayList<Coin> coinList) {
    this.coinArrayList = coinList;
  }*/
  public QuotationFragment() {
    //coinArrayList = new ArrayList<>();
    //listcoin = new ListCoin();
  }

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    quotationViewModel =
      ViewModelProviders.of(this).get(QuotationViewModel.class);
    view = inflater.inflate(R.layout.fragment_quotation, container, false);
        /*final TextView textView = root.findViewById(R.id.text_slideshow);
        quotationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
    //coinController = new CoinController(getContext());
    listView = view.findViewById(R.id.listview);
    //datasetMediaEscolar = mediaEscolarController.getAllResultadoFinal();

    //coinArrayList = mockFakeData();
    coinArrayList = new ArrayList<>();
    //GetOnlineQuotations task = new GetOnlineQuotations(coinArrayList, getContext());
    //GetOnlineQuotations task = new GetOnlineQuotations(coinArrayList);
    GetOnlineQuotations task = new GetOnlineQuotations();
    task.coinArrayList = coinArrayList;
    task.execute();

    // TODO: mudar p/funcionar c/loader...  https://www.devmedia.com.br/consumindo-dados-de-um-web-service-com-android/33717
    // TODO: analisar estas soluções => https://pt.stackoverflow.com/questions/38170/como-obter-resultado-de-uma-tarefa-ass%C3%ADncrona-no-android
    coinArrayList = task.coinArrayList;
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
