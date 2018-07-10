package com.solstice.stockticker.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class StockSummaryMonthParams extends StockSummaryParams {

  @NotEmpty(message = "Date is required")
  @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])$", message = "Date format should be yyyy-mm")
  private String date;

  public StockSummaryMonthParams(String ticker, String date) {
    super(ticker);
    this.date = date;
  }
}
