package br.com.bycrr.v1.appcotacoesmoedas.util;

import android.content.Context;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utility {

  // URL do servidor apache
  // informe o enderço IP se estiver rodando em seu computador
  // informe a URL se estiver rodando em uma hospedagem
  //public static final String URL_WEB_SERVICE = "http://192.168.1.141/mediaescolar/";
  public static final String URL_WEB_SERVICE = "https://economia.awesomeapi.com.br/all/";
  public static final String URL_COINS = "BTC-BRL,USD-BRL,EUR-BRL,GBP-BRL";
  //public static final String URL_COINS = "BTC-BRL";

  // tempo máximo p/considerar um TIMEOUT p/conectar ao apache (conectando)
  public static final int CONNECTION_TIMEOUT = 10000; // 10 segundos

  // tempo máximo para considerar erro de resposta do apache (lendo dados)
  public static final int READ_TIMEOUT = 15000; // 15 segundos

  private static HttpURLConnection connection;

  public static String formatValue(Double valor) {
    DecimalFormat df = new DecimalFormat("#,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
    return df.format(valor);
  }

  public static String getSymbol(String code) {
    HashMap<String, String> symbols = new HashMap<>();
    symbols.put("BTC", "BTC$ ");
    symbols.put("USD", "US$ ");
    symbols.put("EUR", "EUR$ ");
    symbols.put("GBP", "LS$ ");
    symbols.put("BRL", "R$ ");
    return symbols.get(code);
  }

  public static void showMessage(Context context, String message) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }

  public static String addCodeUrlCoins(String code, String urlCoins) {
    urlCoins = urlCoins.concat("," + code + "-BRL");
    if (urlCoins.charAt(0) == ',') urlCoins = urlCoins.substring(1);
    if (urlCoins.charAt(urlCoins.length()-1) == ',') urlCoins = urlCoins.substring(0,urlCoins.length()-1);
    return urlCoins;
  }

  public static String delCodeUrlCoins(String code, String urlCoins) {
    urlCoins = urlCoins.replace(code + "-BRL", "");
    if (urlCoins.charAt(0) == ',') urlCoins = urlCoins.substring(1);
    if (urlCoins.charAt(urlCoins.length()-1) == ',') urlCoins = urlCoins.substring(0,urlCoins.length()-1);
    return urlCoins;
  }

  public static List<String> getCodeList(String urlCoins) {
    //ArrayList<String> arrayList = new ArrayList<>();
    List<String> list = Arrays.asList(urlCoins.split(Pattern.quote(",")));
    return list;
  }
}
