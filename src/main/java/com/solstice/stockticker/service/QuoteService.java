package com.solstice.stockticker.service;

import com.solstice.stockticker.dto.StockSummary;
import com.solstice.stockticker.repository.QuoteRepository;
import org.springframework.stereotype.Service;
@Service
public class QuoteService {
  private QuoteRepository repository;

  public QuoteService(QuoteRepository repository) {
    this.repository = repository;
  }
  public StockSummary findSummary(String symbol, String date){
      return repository.findSummary(symbol, date);
  }
}
