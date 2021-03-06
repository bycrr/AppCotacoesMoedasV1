package br.com.bycrr.v1.appcotacoesmoedas.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.ui.coins.CoinsFragment;
import br.com.bycrr.v1.appcotacoesmoedas.ui.config.ConfigFragment;
import br.com.bycrr.v1.appcotacoesmoedas.ui.quotation.QuotationFragment;
import br.com.bycrr.v1.appcotacoesmoedas.util.GetOnlineQuotations;
import br.com.bycrr.v1.appcotacoesmoedas.util.SharedPrefManager;
import br.com.bycrr.v1.appcotacoesmoedas.util.Utility;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  private AppBarConfiguration mAppBarConfiguration;
  FragmentManager fragmentManager;
  SharedPrefManager sharedPrefManager;
  String urlCoins;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    MobileAds.initialize(this, new OnInitializationCompleteListener() {
      @Override
      public void onInitializationComplete(InitializationStatus initializationStatus) {
      }
    });
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();*/
        GetOnlineQuotations task = new GetOnlineQuotations(getApplicationContext());
        task.execute();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, new QuotationFragment()).commit();
      }
    });
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    fragmentManager = getSupportFragmentManager();
    GetOnlineQuotations task = new GetOnlineQuotations(getApplicationContext());
    task.execute();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onSupportNavigateUp() {
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    return NavigationUI.navigateUp(navController, mAppBarConfiguration)
      || super.onSupportNavigateUp();
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.nav_config) {
      setTitle("Configuração");
      fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, new ConfigFragment()).commit();

    } else if (id == R.id.nav_coins) {
      setTitle("Moedas");
      fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, new CoinsFragment()).commit();

    } else if (id == R.id.nav_quotation) {
      setTitle("Cotações");
      fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, new QuotationFragment()).commit();

    } else if (id == R.id.nav_share) {
      // prá compartilhar via e-mail:
      Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
      sharingIntent.setType("text/plain");
      String shareBody = "Clica aqui: https://play.google.com/store/apps/details?id=br.com.bycrr.v1.appcotacoesmoedas";
      sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Olha que app muito tri: " + this.getResources().getString(R.string.app_name));
      sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
      startActivity(Intent.createChooser(sharingIntent, "Compartilhar via"));
      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      drawer.closeDrawers();
      return true;

      /*Intent shareIntent = new Intent();
      shareIntent.setAction(Intent.ACTION_VIEW);
      shareIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.facebook.katana"));
      startActivity(shareIntent);*/

    } else if (id == R.id.nav_exit) {
      finish();
    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_sair) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void onCheckboxClicked(View view) {
    // Is the view now checked?
    boolean checked = ((Switch) view).isChecked();
    sharedPrefManager = new SharedPrefManager();
    urlCoins = sharedPrefManager.readUrlCoins(getApplicationContext());
    String code = null;

    // Check which checkbox was clicked
    switch (view.getId()) {

      case R.id.checkbox_btc:
        code = "BTC";
        break;

      case R.id.checkbox_usd:
        code = "USD";
        break;

      case R.id.checkbox_eur:
        code = "EUR";
        break;

      case R.id.checkbox_gbp:
        code = "GBP";
        break;

      case R.id.checkbox_usdt:
        code = "USDT";
        break;

      case R.id.checkbox_cad:
        code = "CAD";
        break;

      case R.id.checkbox_aud:
        code = "AUD";
        break;

      case R.id.checkbox_ars:
        code = "ARS";
        break;

      case R.id.checkbox_jpy:
        code = "JPY";
        break;

      case R.id.checkbox_chf:
        code = "CHF";
        break;

      case R.id.checkbox_cny:
        code = "CNY";
        break;

      /*case R.id.checkbox_yls:
        code = "YLS";
        break;*/

      case R.id.checkbox_ltc:
        code = "LTC";
        break;

      case R.id.checkbox_eth:
        code = "ETH";
        break;

      case R.id.checkbox_xrp:
        code = "XRP";
        break;
    }
    if (code != null) {

      if (checked) {
        urlCoins = Utility.addCodeUrlCoins(code, urlCoins);

      } else {
        urlCoins = Utility.delCodeUrlCoins(code, urlCoins);
      }
    }
    sharedPrefManager.saveUrlCoins(urlCoins, getApplicationContext());
  }

  public void onConfigClicked(View view) {
    // Is the view now checked?
    boolean checked = ((RadioButton) view).isChecked();
    sharedPrefManager = new SharedPrefManager();
    //urlCoins = sharedPrefManager.readUrlCoins(getApplicationContext());
    String config = null;
    String value = "erro";

    // Check which checkbox was clicked
    switch (view.getId()) {

      case R.id.radioAscending:
        config = "directAscDes";
        value = "asc";
        break;

      case R.id.radioDescending:
        config = "directAscDes";
        value = "des";
        break;

      case R.id.radioShowIconCoin:
        config = "showIconFlag";
        value = "icon";
        break;

      case R.id.radioShowFlagCoin:
        config = "showIconFlag";
        value = "flag";
        break;

      case R.id.radioOrderCoin:
        config = "order";
        value = "coin";
        break;

      case R.id.radioOrderDate:
        config = "order";
        value = "date";
        break;

      case R.id.radioOrderValue:
        config = "order";
        value = "value";
        break;
    }
    if (config != null) {

      if (checked) {
        sharedPrefManager.saveConfig(config, value, getApplicationContext());

      } else {
        sharedPrefManager.saveConfig(config, " ", getApplicationContext());
      }
    }
  }
}
