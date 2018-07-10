package com.solstice.stockticker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Stock {

  @Id
  @GeneratedValue
  private Long id;

  private String symbol;

  public Stock(String symbol) {
    this.symbol = symbol;
  }

  public Stock() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Stock stock = (Stock) o;
    return Objects.equals(symbol, stock.symbol);
  }

  @Override
  public int hashCode() {

    return Objects.hash(symbol);
  }
}
