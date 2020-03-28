package br.com.bycrr.v1.appcotacoesmoedas.util;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;

public class GetOnlineQuotations extends AsyncTask<String, String, String> {

  //ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
  HttpURLConnection connection;
  URL url = null;
  Uri.Builder builder;
  Context context;

  //public GetOnlineQuotations(ArrayList<Coin> coinList, Context context) {
  //public GetOnlineQuotations(ArrayList<Coin> coinList) {
  public GetOnlineQuotations(Context context) {
    this.builder = new Uri.Builder();
    this.context = context;
    //builder.appendQueryParameter("app", "MediaEscolarV1");
  }

  @Override
  protected void onPreExecute() {
    Log.i("WebService", "Atualizando cotações...");
      /*progressDialog.setMessage("Sincronizando Sistema, por favor espere...");
      progressDialog.setCancelable(false);
      progressDialog.show();*/
  }

  @Override
  protected String doInBackground(String... strings) {

    //public static ArrayList<Coin> getOnlineQuotations(Context context) {
    //URL url = null;
    //Uri.Builder builder = new Uri.Builder();

    // montar a URL com o endereço da API
    try {
      url = new URL(Utility.URL_WEB_SERVICE + Utility.MOEDAS);

    } catch (MalformedURLException e) {
      Log.e("WebService", "MalformedURLException - " + e.getMessage());

    } catch (Exception erro) {
      Log.e("WebService", "Exception - " + erro.getMessage());
    }

    try {
      connection = (HttpURLConnection) url.openConnection();
      connection.setConnectTimeout(Utility.CONNECTION_TIMEOUT);
      connection.setReadTimeout(Utility.READ_TIMEOUT);
      connection.setRequestMethod("GET");
      //connection.setRequestProperty("charset", "utf-8");
      connection.setRequestProperty("Accept-Charset", "UTF-8");
      /*connection.setDoInput(true);
      connection.setDoOutput(true);*/
      /*connection.connect();
      String query = builder.build().getEncodedQuery();
      OutputStream outputStream = connection.getOutputStream();
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
      bufferedWriter.write(query);
      bufferedWriter.flush();
      bufferedWriter.close();
      outputStream.close();*/
      connection.connect();

    } catch (IOException e) {
      Log.e("WebService", "IOException - " + e.getMessage());
    }

    // recebe JSON a resposta do servidor
    try {
      int response_code = connection.getResponseCode();
      // 200 ok
      // 403 forbideen
      // 404 not found
      // 500 server internal error
      if (response_code == HttpURLConnection.HTTP_OK) {
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
          result.append(line);
        }
        Log.i("WebService", "Atualizadas cotações com sucesso.");
        extractJson(result.toString());
        return result.toString();

      } else {
        Log.e("WebService", "response_code = " + response_code);
        return "Erro de conexão - response_code = " + response_code;
      }
    } catch (IOException e) {
      Log.e("WebService", "IOException - " + e.getMessage());
      return e.toString();

    } finally {
      connection.disconnect();
    }
    //return null;
  }

  @Override
  protected void onPostExecute(String result) {
  }

  private void extractJson(String result) {
    //ArrayList<Coin> listCoins = new ArrayList<>();
    Coin coin;
    SharedPrefManager sharedPrefManager = new SharedPrefManager();

    try {
      JSONObject objJson = new JSONObject(result);

      for (int i = 0; i < 4; i++) {
        JSONObject jsonArrayUSD = objJson.getJSONObject(Utility.getCode(i));
        coin = new Coin();
        coin.setCode(jsonArrayUSD.getString("code"));
        coin.setTitle(jsonArrayUSD.getString("name"));
        coin.setSymbol(Utility.getSymbol(jsonArrayUSD.getString("code")));
        coin.setValueBid(BigDecimal.valueOf(jsonArrayUSD.getDouble("bid")));
        coin.setValueAsk(BigDecimal.valueOf(jsonArrayUSD.getDouble("ask")));
        coin.setDateTime(jsonArrayUSD.getString("create_date"));
        sharedPrefManager.saveSharedPreferences(coin, context);
      }

      /*JSONObject jsonArrayEUR = objJson.getJSONObject("EUR");
      coin = new Coin();
      coin.setCode(jsonArrayEUR.getString("code"));
      coin.setTitle(jsonArrayEUR.getString("name"));
      coin.setSymbol(Utility.getSymbol(jsonArrayEUR.getString("code")));
      coin.setValueBid(BigDecimal.valueOf(jsonArrayEUR.getDouble("bid")));
      coin.setValueAsk(BigDecimal.valueOf(jsonArrayEUR.getDouble("ask")));
      coin.setDateTime(jsonArrayEUR.getString("create_date"));
      sharedPrefManager.saveSharedPreferences(coin, context);

      JSONObject jsonArrayBTC = objJson.getJSONObject("BTC");
      coin = new Coin();
      coin.setCode(jsonArrayBTC.getString("code"));
      coin.setTitle(jsonArrayBTC.getString("name"));
      coin.setSymbol(Utility.getSymbol(jsonArrayBTC.getString("code")));
      coin.setValueBid(BigDecimal.valueOf(jsonArrayBTC.getDouble("bid")));
      coin.setValueAsk(BigDecimal.valueOf(jsonArrayBTC.getDouble("ask")));
      coin.setDateTime(jsonArrayBTC.getString("create_date"));
      sharedPrefManager.saveSharedPreferences(coin, context);*/

    } catch (JSONException e) {
      Log.e("WebService", "JSONException - " + e.getMessage());

    } finally {
      /*if (progressDialog != null && progressDialog.isShowing()) {
        progressDialog.dismiss();
      }*/
    }
  }
}
