package com.solstice.stockticker.dto;

import java.math.BigDecimal;

public class StockSummary {
  private BigDecimal highPrice;
  private BigDecimal lowPrice;
  private Long totalVolume;

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
}
