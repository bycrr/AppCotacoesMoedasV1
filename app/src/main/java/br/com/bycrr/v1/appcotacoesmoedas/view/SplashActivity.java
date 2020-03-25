package br.com.bycrr.v1.appcotacoesmoedas.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import br.com.bycrr.v1.appcotacoesmoedas.R;

/**
 * Tela Splash é uma tela de apresentação inicial do
 * aplicativo, pode ser utilizada para executar atualizações
 * enquanto carrega o app
 */
public class SplashActivity extends AppCompatActivity {

  private static final int SPLASH_TIME_OUT = 1000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    apresentarTelaSplash();
  }

  private void apresentarTelaSplash() {
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent telaPrincipal = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(telaPrincipal);
        finish();
      }
    }, SPLASH_TIME_OUT);
  }
}
