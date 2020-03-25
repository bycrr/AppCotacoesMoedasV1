package br.com.bycrr.v1.appcotacoesmoedas.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import br.com.bycrr.v1.appcotacoesmoedas.R;
import br.com.bycrr.v1.appcotacoesmoedas.ui.coins.CoinsFragment;
import br.com.bycrr.v1.appcotacoesmoedas.ui.config.ConfigFragment;
import br.com.bycrr.v1.appcotacoesmoedas.ui.quotation.QuotationFragment;
import br.com.bycrr.v1.appcotacoesmoedas.util.GetOnlineQuotations;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  private AppBarConfiguration mAppBarConfiguration;
  FragmentManager fragmentManager;
  /*ArrayList<Coin> coinArrayList;
  ListCoin listCoin;*/

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();
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
    /*this.coinArrayList = new ArrayList<>();
    GetOnlineQuotations task = new GetOnlineQuotations(coinArrayList, getApplicationContext());
    listCoin.setCoinArrayList(coinArrayList);
    //this.coinArrayList = task.coinArrayList;*/
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

  /*@Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_sair) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }*/

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
      setTitle("Quotações");
      //fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, new QuotationFragment(this.coinArrayList)).commit();
      fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, new QuotationFragment()).commit();

    } else if (id == R.id.nav_share) {
    }
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
