package com.solstice.stockticker.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Quote {

  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  private Stock stock;
  private BigDecimal price;
  private Long volume;
  private Date date;

  public Quote() {
  }

  public Quote(Stock stock, BigDecimal price, Long volume, Date date) {
    this.stock = stock;
    this.price = price;
    this.volume = volume;
    this.date = date;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Long getVolume() {
    return volume;
  }

  public void setVolume(Long volume) {
    this.volume = volume;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }
}
