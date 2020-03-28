package br.com.bycrr.v1.appcotacoesmoedas.model;

import java.math.BigDecimal;

public class Coin {
  private String title;         // name
  private String code;          // code
  private String symbol;        // "US$", ...
  private String dateTime;      // create_date
  private BigDecimal valueBid;  // compra
  private BigDecimal valueAsk;  // venda
  private String image;         // icon

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public BigDecimal getValueBid() {
    return valueBid;
  }

  public void setValueBid(BigDecimal valueBid) {
    this.valueBid = valueBid;
  }

  public BigDecimal getValueAsk() {
    return valueAsk;
  }

  public void setValueAsk(BigDecimal valueAsk) {
    this.valueAsk = valueAsk;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
