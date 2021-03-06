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
  String showIconFlag;

  private static class ViewHolder {
    TextView txtTituloMoeda;
    TextView txtDataHora;
    TextView txtValor;
    TextView txtSimbolo;
    ImageView imgLogo;
  }

  public QuotationListAdapter(ArrayList<Coin> datasetCoin, String showIconFlag, Context context) {
    super(context, R.layout.listview_quotation, datasetCoin);
    this.dados = datasetCoin;
    this.context = context;
    this.showIconFlag = showIconFlag;
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
    Drawable icon = getContext().getResources().getDrawable(R.mipmap.ic_coin);  // generic coin

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
    if (coin.getCode().equals("BTC")) {
      icon = getContext().getResources().getDrawable(R.mipmap.ic_launcher);

    } else if (coin.getCode().equals("USD") || coin.getCode().equals("USDT")) {
      icon = getContext().getResources().getDrawable(showIconFlag.equals("icon") ? R.mipmap.ic_dollar : R.mipmap.ic_usd_flag);

    } else if (coin.getCode().equals("EUR")) {
      icon = getContext().getResources().getDrawable(showIconFlag.equals("icon") ? R.mipmap.ic_euro : R.mipmap.ic_eur_flag);

    } else if (coin.getCode().equals("GBP")) {
      icon = getContext().getResources().getDrawable(showIconFlag.equals("icon") ? R.mipmap.ic_libra : R.mipmap.ic_gbp_flag);

    } else if (coin.getCode().equals("CAD")) {
      icon = getContext().getResources().getDrawable(showIconFlag.equals("icon") ? R.mipmap.ic_coin : R.mipmap.ic_cad_flag);

    } else if (coin.getCode().equals("AUD")) {
      icon = getContext().getResources().getDrawable(showIconFlag.equals("icon") ? R.mipmap.ic_coin : R.mipmap.ic_aud_flag);

    } else if (coin.getCode().equals("ARS")) {
      icon = getContext().getResources().getDrawable(showIconFlag.equals("icon") ? R.mipmap.ic_coin : R.mipmap.ic_ars_flag);

    } else if (coin.getCode().equals("JPY")) {
      icon = getContext().getResources().getDrawable(showIconFlag.equals("icon") ? R.mipmap.ic_coin : R.mipmap.ic_jpy_flag);

    } else if (coin.getCode().equals("CHF")) {
      icon = getContext().getResources().getDrawable(showIconFlag.equals("icon") ? R.mipmap.ic_coin : R.mipmap.ic_chf_flag);

    } else if (coin.getCode().equals("CNY")) {
      icon = getContext().getResources().getDrawable(showIconFlag.equals("icon") ? R.mipmap.ic_coin : R.mipmap.ic_cny_flag);

    } else if (coin.getCode().equals("LTC")) {
      icon = getContext().getResources().getDrawable(R.mipmap.ic_litecoin);

    } else if (coin.getCode().equals("ETH")) {
      icon = getContext().getResources().getDrawable(R.mipmap.ic_ethereum);

    } else if (coin.getCode().equals("XRP")) {
      icon = getContext().getResources().getDrawable(R.mipmap.ic_ripple);
    }
    linha.txtTituloMoeda.setText(coin.getTitle());
    linha.txtDataHora.setText(coin.getDateTime());
    linha.txtValor.setText(Utility.formatValue(coin.getValueBid().doubleValue()));
    linha.txtSimbolo.setText("R$ ");
    linha.imgLogo.setImageDrawable(icon);
    return linhaDataSet;
  }

}
