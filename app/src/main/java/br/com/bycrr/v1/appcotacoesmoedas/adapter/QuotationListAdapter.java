package br.com.bycrr.v1.appcotacoesmoedas.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;

public class QuotationListAdapter extends ArrayAdapter<Coin> implements View.OnClickListener {

  Context context;
  ArrayList<Coin> dados;
  Coin coin;
  ViewHolder linha;

  private static class ViewHolder {
    TextView txtTituloMoeda;
    TextView txtDataHora;
    TextView txtValor;
    /*ImageView imgLogo;
    ImageView imgConsultar;
    ImageView imgEditar;
    ImageView imgDeletar;
    ImageView imgSalvar;*/
  }

  public QuotationListAdapter(ArrayList<Coin> datasetCoin, Context context) {
    super(context, R.layout.listview_quotation, datasetCoin);
    this.dados = datasetCoin;
    this.context = context;
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
    //ViewHolder linha;

    if (linhaDataSet == null) {
      linha = new ViewHolder();
      LayoutInflater quotationFinalList = LayoutInflater.from(getContext());
      linhaDataSet = quotationFinalList.inflate(R.layout.listview_quotation, parent, false);
      linha.txtTituloMoeda = linhaDataSet.findViewById(R.id.txtTituloMoeda);
      linha.txtDataHora = linhaDataSet.findViewById(R.id.txtDataHora);
      linha.txtValor = linhaDataSet.findViewById(R.id.txtValor);
      /*linha.imgLogo = linhaDataSet.findViewById(R.id.imgLogo);*/
      //linha.imgSalvar = linhaDataSet.findViewById(R.id.imgSalvar);
      linhaDataSet.setTag(linha);

    } else {
      linha = (ViewHolder) linhaDataSet.getTag();
    }
    linha.txtTituloMoeda.setText(coin.getTitle());
    linha.txtDataHora.setText(coin.getDateTime());
    linha.txtValor.setText(coin.getValue().toString());
    /*linha.imgLogo.setOnClickListener(this);*/
    //linha.imgSalvar.setOnClickListener(this);
    /*linha.imgLogo.setTag(position);*/
    //linha.imgSalvar.setTag(position);

    //return super.getView(position, convertView, parent);
    return linhaDataSet;
  }

}
