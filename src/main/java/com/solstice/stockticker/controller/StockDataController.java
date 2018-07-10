package com.solstice.stockticker.controller;

import com.solstice.stockticker.dto.StockSummary;
import com.solstice.stockticker.service.QuoteLoadingService;
import com.solstice.stockticker.service.QuoteService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
  public StockSummary get(@PathVariable("ticker") String ticker,
                          @PathVariable("date")String date){
    return quoteService.findSummary(ticker, date);
  }
}
