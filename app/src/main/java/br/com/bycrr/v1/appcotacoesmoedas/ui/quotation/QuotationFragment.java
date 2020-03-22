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

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.adapter.QuotationListAdapter;
import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;

public class QuotationFragment extends Fragment {

  private QuotationViewModel quotationViewModel;
  ListView listView;
  //CoinController coinController;
  View view;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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
    Coin coin = new Coin();
    coin.setTitle("BitCoin");
    coin.setDateTime("22/03/2020 16:30h");
    coin.setValue(BigDecimal.valueOf(10d));

    // TODO: arrumar estes mocks
    ArrayList<Coin> coinArrayList = new ArrayList<>();
    coinArrayList.add(coin);

    //final ResultadoFinalListAdapter adapter = new ResultadoFinalListAdapter(datasetMediaEscolar, getContext());
    final QuotationListAdapter adapter = new QuotationListAdapter(coinArrayList, getContext());
    listView.setAdapter(adapter);

    return view;
  }
}
