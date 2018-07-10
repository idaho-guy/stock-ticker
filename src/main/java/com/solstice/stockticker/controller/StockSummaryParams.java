package com.solstice.stockticker.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class StockSummaryParams {

  @NotEmpty(message = "Stock ticker is required")
  private String ticker;

  public StockSummaryParams(String ticker) {
    this.ticker = ticker;
  }
}

