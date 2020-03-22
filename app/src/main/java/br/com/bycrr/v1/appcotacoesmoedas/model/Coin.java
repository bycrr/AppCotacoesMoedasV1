package br.com.bycrr.v1.appcotacoesmoedas.model;

import java.math.BigDecimal;

public class Coin {
  private String title;
  private String dateTime;
  private BigDecimal value;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }
}
