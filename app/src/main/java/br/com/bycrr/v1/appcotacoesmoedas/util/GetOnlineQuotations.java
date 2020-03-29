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
import java.util.List;

import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;

public class GetOnlineQuotations extends AsyncTask<String, String, String> {

  //ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
  HttpURLConnection connection;
  URL url = null;
  Uri.Builder builder;
  Context context;
  SharedPrefManager sharedPrefManager = new SharedPrefManager();
  String urlCoins;

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

    // montar a URL com o endereço da API
    try {
      urlCoins = sharedPrefManager.readUrlCoins(context);
      if (urlCoins == null) {
        urlCoins = Utility.URL_COINS;
        sharedPrefManager.saveUrlCoins(urlCoins, context);
      }
      url = new URL(Utility.URL_WEB_SERVICE + urlCoins);

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
      connection.setRequestProperty("Accept-Charset", "UTF-8");
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
        Log.i("WebService", "Atualizadas cotações com sucesso");
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
  }

  @Override
  protected void onPostExecute(String result) {
    if (result != null)
      Utility.showMessage(context, "Atualizadas cotações com sucesso");
  }

  private void extractJson(String result) {
    Coin coin;

    try {
      JSONObject objJson = new JSONObject(result);
      List<String> codeList = Utility.getCodeList(urlCoins);

      for (String code: codeList) {
        JSONObject jsonArrayCoin = objJson.getJSONObject(code.substring(0,3));
        coin = new Coin();
        coin.setCode(jsonArrayCoin.getString("code"));
        coin.setTitle(jsonArrayCoin.getString("name"));
        coin.setSymbol(Utility.getSymbol(jsonArrayCoin.getString("code")));
        coin.setValueBid(BigDecimal.valueOf(jsonArrayCoin.getDouble("bid")));
        coin.setValueAsk(BigDecimal.valueOf(jsonArrayCoin.getDouble("ask")));
        coin.setDateTime(jsonArrayCoin.getString("create_date"));
        sharedPrefManager.saveCoin(coin, context);
      }

    } catch (JSONException e) {
      Log.e("WebService", "JSONException - " + e.getMessage());

    } finally {
      /*if (progressDialog != null && progressDialog.isShowing()) {
        progressDialog.dismiss();
      }*/
    }
  }
}
