package com.solstice.stockticker.dto;

import java.math.BigDecimal;

public class StockSummary {
  private BigDecimal highPrice;
  private BigDecimal lowPrice;
  private Long totalVolume;
  private BigDecimal closingPrice;

  public StockSummary(BigDecimal highPrice, BigDecimal lowPrice, Long totalVolume) {
    this.highPrice = highPrice;
    this.lowPrice = lowPrice;
    this.totalVolume = totalVolume;
  }

  public StockSummary() {
  }

  public BigDecimal getHighPrice() {
    return highPrice;
  }

  public BigDecimal getLowPrice() {
    return lowPrice;
  }

  public Long getTotalVolume() {
    return totalVolume;
  }

  public BigDecimal getClosingPrice() {
    return closingPrice;
  }

  public void setClosingPrice(BigDecimal closingPrice) {
    this.closingPrice = closingPrice;
  }
}
