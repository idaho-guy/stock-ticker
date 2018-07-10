package com.solstice.stockticker.service;

import com.google.gson.Gson;
import com.solstice.stockticker.dto.QuoteDto;
import com.solstice.stockticker.model.Quote;
import com.solstice.stockticker.model.Stock;
import com.solstice.stockticker.repository.QuoteRepository;
import com.solstice.stockticker.repository.StockRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Primary
public class ClassPathQuoteLoadingSystem implements QuoteLoadingService{
  private QuoteRepository quoteRepository;
  private StockRepository stockRepository;

  public ClassPathQuoteLoadingSystem(QuoteRepository quoteRepository, StockRepository stockRepository) {
    this.quoteRepository = quoteRepository;
    this.stockRepository = stockRepository;
  }

  @Override
  public void loadQuotes() {
    try(InputStreamReader r = new InputStreamReader(getClass().getResourceAsStream("/week2-stocks.json"))){
      List<QuoteDto> quoteDtos = Arrays.asList(new Gson().fromJson(r, QuoteDto[].class));
      List<Stock> stocks = quoteDtos.stream().map(q -> new Stock(q.getSymbol())).distinct().collect(Collectors.toList());
      Map<String, Stock> updated = stocks.stream().map(s ->
          stockRepository.save(s)).collect(Collectors.toMap(s -> s.getSymbol(), s -> s));
      List<Quote> quotes = quoteDtos.stream().map(q -> new Quote(updated.get(q.getSymbol()),q.getPrice(), q.getVolume(), q.getDate())).collect(Collectors.toList());
      quoteRepository.saveAll(quotes);
    }catch (Exception e){
      throw new RuntimeException(e);
    }
  }
}
