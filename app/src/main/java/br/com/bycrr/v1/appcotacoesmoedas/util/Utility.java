package br.com.bycrr.v1.appcotacoesmoedas.util;

import android.content.Context;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Utility {

  // URL do servidor apache
  // informe o enderço IP se estiver rodando em seu computador
  // informe a URL se estiver rodando em uma hospedagem
  //public static final String URL_WEB_SERVICE = "http://192.168.1.141/mediaescolar/";
  public static final String URL_WEB_SERVICE = "https://economia.awesomeapi.com.br";

  // tempo máximo p/considerar um TIMEOUT p/conectar ao apache (conectando)
  public static final int CONNECTION_TIMEOUT = 10000; // 10 segundos

  // tempo máximo para considerar erro de resposta do apache (lendo dados)
  public static final int READ_TIMEOUT = 15000; // 15 segundos

  private static HttpURLConnection connection;

  public static String formatValue(Double valor) {
    DecimalFormat df = new DecimalFormat("#,###,##0.00");
    return df.format(valor);
  }

  public static String getSymbol(String code) {
    HashMap<String, String> symbols = new HashMap<>();
    symbols.put("USD", "US$ ");
    symbols.put("EUR", "E$ ");
    symbols.put("BRL", "R$ ");
    symbols.put("BTC", "B$ ");
    return symbols.get(code);
  }

  public static void showMessage(Context context, String message) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }

}
