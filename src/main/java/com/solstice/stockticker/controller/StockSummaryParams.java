package com.solstice.stockticker.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class StockSummaryParams {

  @NotEmpty(message = "Stock ticker is required")
  private String ticker;
  @NotEmpty(message = "Date is required")
  @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Date format should be yyyy-mm-dd")
  private String date;

  public StockSummaryParams(String ticker, String date) {
    this.ticker = ticker;
    this.date = date;
  }
}

