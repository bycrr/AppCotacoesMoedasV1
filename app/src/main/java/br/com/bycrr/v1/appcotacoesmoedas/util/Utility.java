package br.com.bycrr.v1.appcotacoesmoedas.util;

import android.content.Context;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Utility {

  // URL do servidor apache
  // informe o enderço IP se estiver rodando em seu computador
  // informe a URL se estiver rodando em uma hospedagem
  //public static final String URL_WEB_SERVICE = "http://192.168.1.141/mediaescolar/";
  public static final String URL_WEB_SERVICE = "https://economia.awesomeapi.com.br/all/";
  public static final String MOEDAS = "USD-BRL,EUR-BRL,BTC-BRL,GBP-BRL";
  private static ArrayList<String> listCodes = new ArrayList<>();

  // tempo máximo p/considerar um TIMEOUT p/conectar ao apache (conectando)
  public static final int CONNECTION_TIMEOUT = 10000; // 10 segundos

  // tempo máximo para considerar erro de resposta do apache (lendo dados)
  public static final int READ_TIMEOUT = 15000; // 15 segundos

  private static HttpURLConnection connection;

  public static String formatValue(Double valor) {
    DecimalFormat df = new DecimalFormat("#,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
    return df.format(valor);
  }

  public static void setCode(String code) {
    Utility.listCodes.add(code);
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

  public static String getCode(int pos) {
    listCodes.add("BTC");
    listCodes.add("USD");
    listCodes.add("EUR");
    listCodes.add("GBP");
    listCodes.add("BRL");
    return listCodes.get(pos);
  }

  public static void showMessage(Context context, String message) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }
}
