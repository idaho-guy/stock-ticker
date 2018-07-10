package com.solstice.stockticker.controller;

import com.solstice.stockticker.dto.StockSummary;
import com.solstice.stockticker.service.QuoteLoadingService;
import com.solstice.stockticker.service.QuoteService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoadStockDataController {

  private QuoteLoadingService loader;

  private QuoteService quoteService;

  public LoadStockDataController(QuoteLoadingService loader,QuoteService quoteService) {
    this.loader = loader;
    this.quoteService = quoteService;
  }

  @PostMapping("/load")
  public void loadRecords(){
    loader.loadQuotes();
  }

  @GetMapping("/{ticker}/{date}")
  @ResponseBody
  public StockSummary get(@PathVariable("ticker") String ticker, @PathVariable("date")String date){
    return quoteService.findSummary(ticker, date);
  }
}
