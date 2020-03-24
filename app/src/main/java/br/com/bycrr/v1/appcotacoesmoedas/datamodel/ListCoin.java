package br.com.bycrr.v1.appcotacoesmoedas.datamodel;

import java.util.ArrayList;

import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;

public class ListCoin {
  private static ArrayList<Coin> coinArrayList;

  public ArrayList<Coin> getCoinArrayList() {
    return coinArrayList;
  }

  public void setCoinArrayList(ArrayList<Coin> coinArrayList) {
    this.coinArrayList = coinArrayList;
  }
}
