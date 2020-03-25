package br.com.bycrr.v1.appcotacoesmoedas.util;

import android.content.Context;

import java.math.BigDecimal;

import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;

public class SharedPrefManager {

  private static final String SHARED_PREFER_NAME = "AppCotacoesMoedas";

  public void saveSharedPreferences(Coin coin, Context context) {
    android.content.SharedPreferences appCotacoesMoedasPref = context.getSharedPreferences(this.SHARED_PREFER_NAME, Context.MODE_PRIVATE);
    //SharedPreferences appCotacoesMoedasPref = PreferenceManager.getDefaultSharedPreferences(this);
    //SharedPreferences sharedPref = getSharedPreferences("patientId", Context.MODE_PRIVATE);
    //SharedPreferences prefs = android.preference.PreferenceManager.getDefaultSharedPreferences(this);

    android.content.SharedPreferences.Editor editor = appCotacoesMoedasPref.edit();
    editor.putString("code" + coin.getCode(), coin.getCode());
    editor.putString("title" + coin.getCode(), coin.getTitle());
    editor.putString("symbol" + coin.getCode(), coin.getSymbol());
    editor.putFloat("valueBid" + coin.getCode(), coin.getValueBid().floatValue());
    editor.putFloat("valueAsk" + coin.getCode(), coin.getValueAsk().floatValue());
    editor.putString("dateTime" + coin.getCode(), coin.getDateTime());
    editor.commit();

  }

  public Coin lerSharedPreferences(String code, Context context)  {
    android.content.SharedPreferences appCotacoesMoedasPref = context.getSharedPreferences(this.SHARED_PREFER_NAME, Context.MODE_PRIVATE);
    //SharedPreferences appCotacoesMoedasPref = PreferenceManager.getDefaultSharedPreferences(this);
    Coin coin = new Coin();
    coin.setCode(appCotacoesMoedasPref.getString("code" + code, "erro"));
    coin.setTitle(appCotacoesMoedasPref.getString("title" + code, " "));
    coin.setSymbol(appCotacoesMoedasPref.getString("symbol" + code, " "));
    coin.setValueBid(BigDecimal.valueOf(appCotacoesMoedasPref.getFloat("valueBid" + code, 0f)));
    coin.setValueAsk(BigDecimal.valueOf(appCotacoesMoedasPref.getFloat("valueAsk" + code, 0f)));
    coin.setDateTime(appCotacoesMoedasPref.getString("dateTime" + code, " "));
    return coin;
  }

  public void clearSharedPreferences(Context context) {
    android.content.SharedPreferences appCotacoesMoedasPref = context.getSharedPreferences(this.SHARED_PREFER_NAME, Context.MODE_PRIVATE);
    //SharedPreferences appCotacoesMoedasPref = PreferenceManager.getDefaultSharedPreferences(this);
    android.content.SharedPreferences.Editor editor = appCotacoesMoedasPref.edit();
    editor.clear();
    editor.commit();
    //clearMenu();
  }

}
