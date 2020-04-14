package br.com.bycrr.v1.appcotacoesmoedas.util;

import android.content.Context;

import java.math.BigDecimal;

import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;

public class SharedPrefManager {

  private static final String SHARED_PREFER_NAME = "AppCotacoesMoedas";

  public void saveUrlCoins(String urlCoins, Context context) {
    android.content.SharedPreferences appCotacoesMoedasPref = context.getSharedPreferences(this.SHARED_PREFER_NAME, Context.MODE_PRIVATE);
    android.content.SharedPreferences.Editor editor = appCotacoesMoedasPref.edit();
    editor.putString("urlCoins", urlCoins);
    editor.commit();
  }

  public String readUrlCoins(Context context)  {
    android.content.SharedPreferences appCotacoesMoedasPref = context.getSharedPreferences(this.SHARED_PREFER_NAME, Context.MODE_PRIVATE);
    return appCotacoesMoedasPref.getString("urlCoins", null);
  }

  public void saveCoin(Coin coin, Context context) {
    android.content.SharedPreferences appCotacoesMoedasPref = context.getSharedPreferences(this.SHARED_PREFER_NAME, Context.MODE_PRIVATE);
    android.content.SharedPreferences.Editor editor = appCotacoesMoedasPref.edit();
    editor.putString("code" + coin.getCode(), coin.getCode());
    editor.putString("title" + coin.getCode(), coin.getTitle());
    editor.putString("symbol" + coin.getCode(), coin.getSymbol());
    editor.putFloat("valueBid" + coin.getCode(), coin.getValueBid().floatValue());
    editor.putFloat("valueAsk" + coin.getCode(), coin.getValueAsk().floatValue());
    editor.putString("dateTime" + coin.getCode(), coin.getDateTime());
    editor.commit();
  }

  public Coin readCoin(String code, Context context)  {
    android.content.SharedPreferences appCotacoesMoedasPref = context.getSharedPreferences(this.SHARED_PREFER_NAME, Context.MODE_PRIVATE);
    Coin coin = new Coin();
    coin.setCode(appCotacoesMoedasPref.getString("code" + code, "erro"));
    coin.setTitle(appCotacoesMoedasPref.getString("title" + code, " "));
    coin.setSymbol(appCotacoesMoedasPref.getString("symbol" + code, " "));
    coin.setValueBid(BigDecimal.valueOf(appCotacoesMoedasPref.getFloat("valueBid" + code, 0f)));
    coin.setValueAsk(BigDecimal.valueOf(appCotacoesMoedasPref.getFloat("valueAsk" + code, 0f)));
    coin.setDateTime(appCotacoesMoedasPref.getString("dateTime" + code, " "));
    return coin;
  }

  public void saveConfig(String name, String value, Context context) {
    android.content.SharedPreferences appCotacoesMoedasPref = context.getSharedPreferences(this.SHARED_PREFER_NAME, Context.MODE_PRIVATE);
    android.content.SharedPreferences.Editor editor = appCotacoesMoedasPref.edit();
    editor.putString(name, value);
    editor.commit();
  }

  public String readConfig(String name, Context context) {
    android.content.SharedPreferences appCotacoesMoedasPref = context.getSharedPreferences(this.SHARED_PREFER_NAME, Context.MODE_PRIVATE);
    return appCotacoesMoedasPref.getString(name, "erro");
  }

  public void clearSharedPreferences(Context context) {
    android.content.SharedPreferences appCotacoesMoedasPref = context.getSharedPreferences(this.SHARED_PREFER_NAME, Context.MODE_PRIVATE);
    android.content.SharedPreferences.Editor editor = appCotacoesMoedasPref.edit();
    editor.clear();
    editor.commit();
    //clearMenu();
  }

}
