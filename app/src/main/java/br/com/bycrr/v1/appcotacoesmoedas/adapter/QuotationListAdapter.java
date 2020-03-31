package br.com.bycrr.v1.appcotacoesmoedas.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;
import br.com.bycrr.v1.appcotacoesmoedas.util.Utility;

public class QuotationListAdapter extends ArrayAdapter<Coin> implements View.OnClickListener {

  Context context;
  ArrayList<Coin> dados;
  Coin coin;
  ViewHolder linha;

  private static class ViewHolder {
    TextView txtTituloMoeda;
    TextView txtDataHora;
    TextView txtValor;
    TextView txtSimbolo;
    ImageView imgLogo;
  }

  public QuotationListAdapter(ArrayList<Coin> datasetCoin, Context context) {
    super(context, R.layout.listview_quotation, datasetCoin);
    this.dados = datasetCoin;
    this.context = context;
  }

  public void atualizarLista(ArrayList<Coin> novosDados) {
    this.dados.clear();
    this.dados.addAll(novosDados);
    notifyDataSetChanged();
  }

  @Override
  public void registerDataSetObserver(DataSetObserver observer) {
    super.registerDataSetObserver(observer);
  }

  @Override
  public void onClick(View v) {

  }

  @NonNull
  @Override
  public View getView(int position, View linhaDataSet, @NonNull ViewGroup parent) {
    coin = getItem(position);
    Drawable icon = getContext().getResources().getDrawable(R.mipmap.ic_launcher);  // bitcoin

    if (linhaDataSet == null) {
      linha = new ViewHolder();
      LayoutInflater quotationFinalList = LayoutInflater.from(getContext());
      linhaDataSet = quotationFinalList.inflate(R.layout.listview_quotation, parent, false);
      linha.txtTituloMoeda = linhaDataSet.findViewById(R.id.txtTituloMoeda);
      linha.txtDataHora = linhaDataSet.findViewById(R.id.txtDataHora);
      linha.txtValor = linhaDataSet.findViewById(R.id.txtValor);
      linha.txtSimbolo = linhaDataSet.findViewById(R.id.txtSimbolo);
      linha.imgLogo = linhaDataSet.findViewById(R.id.imgLogo);
      linhaDataSet.setTag(linha);

    } else {
      linha = (ViewHolder) linhaDataSet.getTag();
    }
    if (coin.getCode().equals("USD")) {
      icon = getContext().getResources().getDrawable(R.mipmap.ic_dollar);

    } else if (coin.getCode().equals("EUR")) {
      icon = getContext().getResources().getDrawable(R.mipmap.ic_euro);

    } else if (coin.getCode().equals("GBP")) {
      icon = getContext().getResources().getDrawable(R.mipmap.ic_libra);
    }
    linha.txtTituloMoeda.setText(coin.getTitle());
    linha.txtDataHora.setText(coin.getDateTime());
    linha.txtValor.setText(Utility.formatValue(coin.getValueBid().doubleValue()));
    linha.txtSimbolo.setText("R$ ");
    linha.imgLogo.setImageDrawable(icon);
    return linhaDataSet;
  }

}
