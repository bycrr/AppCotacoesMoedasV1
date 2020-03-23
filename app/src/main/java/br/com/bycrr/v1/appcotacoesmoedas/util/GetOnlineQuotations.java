package br.com.bycrr.v1.appcotacoesmoedas.util;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
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
import java.util.ArrayList;

import br.com.bycrr.v1.appcotacoesmoedas.model.Coin;

public class GetOnlineQuotations extends AsyncTask<String, String, String> {

  //ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
  HttpURLConnection connection;
  URL url = null;
  Uri.Builder builder;
  Context context;
  public ArrayList<Coin> coinArrayList;

  public GetOnlineQuotations(ArrayList<Coin> coinList, Context context) {
    this.builder = new Uri.Builder();
    this.context = context;
    this.coinArrayList = coinList;
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
      url = new URL(Utility.URL_WEB_SERVICE + "/all/USD-BRL,EUR-BRL,BTC-BRL");

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
    ArrayList<Coin> listCoins = new ArrayList<>();

    try {
      JSONArray jsonArray = new JSONArray(result);

      if (jsonArray.length() > 0) {
        // salvar os dados no DB SQLite
        //mediaEscolarController.deletarTabela(MediaEscolarDataModel.getTABELA());
        //mediaEscolarController.criarTabela(MediaEscolarDataModel.criarTabela());
        Coin obj;
        JSONObject jsonObject;

        /*for (int i = 0; i < jsonArray.length(); i++) {
          jsonObject = jsonArray.getJSONObject(i);
          obj = extractCoin(jsonObject, jsonObject.getString("code"));
          listCoins.add(obj);
        }*/

      } else {
        Utility.showMessage(context, "Nenhuma dado recebido...");
      }

    } catch (JSONException e) {
      Log.e("WebService", "JSONException - " + e.getMessage());

    } finally {

      /*if (progressDialog != null && progressDialog.isShowing()) {
        progressDialog.dismiss();
      }*/
    }
    //return listCoins;
    this.coinArrayList = listCoins;
  }

  private Coin extractCoin(JSONObject jsonObject, String code) {
    Coin coin = new Coin();

    try {
      //JSONObject jsonObject = new JSONObject(result);
      JSONArray jsonArray = jsonObject.getJSONArray(code);

      if (jsonArray.length() > 0) {
        // salvar os dados no DB SQLite
        //mediaEscolarController.deletarTabela(MediaEscolarDataModel.getTABELA());
        //mediaEscolarController.criarTabela(MediaEscolarDataModel.criarTabela());

        for (int i = 0; i < jsonArray.length(); i++) {
          jsonObject = jsonArray.getJSONObject(i);
          Coin obj = new Coin();
          //obj.setId(jsonObject.getInt(MediaEscolarDataModel.getId()));
          obj.setCode(jsonObject.getString("code"));
          obj.setTitle(jsonObject.getString("name"));
          obj.setSymbol(Utility.getSymbol(jsonObject.getString("code")));
          obj.setValueBid(BigDecimal.valueOf(jsonObject.getDouble("bid")));
          obj.setValueAsk(BigDecimal.valueOf(jsonObject.getDouble("ask")));
          //mediaEscolarController.incluir(obj);
          coin = obj;
        }
      } else {
        Utility.showMessage(context, "Nenhuma dado recebido...");
      }

    } catch (JSONException e) {
      Log.e("WebService", "JSONException - " + e.getMessage());

    } finally {

      /*if (progressDialog != null && progressDialog.isShowing()) {
        progressDialog.dismiss();
      }*/
    }
    return coin;
  }
}
