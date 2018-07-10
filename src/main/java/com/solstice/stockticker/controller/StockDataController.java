package com.solstice.stockticker.controller;

import com.solstice.stockticker.dto.StockSummaryDto;
import com.solstice.stockticker.service.QuoteLoadingService;
import com.solstice.stockticker.service.QuoteService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockDataController {

  private QuoteLoadingService loader;

  private QuoteService quoteService;

  public StockDataController(QuoteLoadingService loader, QuoteService quoteService) {
    this.loader = loader;
    this.quoteService = quoteService;
  }

  @PostMapping("/load")
  public void loadRecords(){
    quoteService.verifyEmpty();
    loader.loadQuotes();
  }

  @GetMapping("/{ticker}/{date}")
  @ResponseBody
  public StockSummaryDto dailySummary(@PathVariable("ticker") String ticker,
                                      @PathVariable("date")String date){
    return quoteService.findSummaryByDate(ticker, date);
  }

  @GetMapping("/month/{ticker}/{date}")
  @ResponseBody
  public StockSummaryDto monthSummary(@PathVariable("ticker") String ticker,
                                      @PathVariable("date")String date){
    return quoteService.findSummaryByMonth(ticker, date);
  }

}
