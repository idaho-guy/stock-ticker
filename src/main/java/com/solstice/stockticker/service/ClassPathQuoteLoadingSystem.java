package com.solstice.stockticker.service;

import com.google.gson.Gson;
import com.solstice.stockticker.model.Quote;
import com.solstice.stockticker.repository.QuoteRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@Primary
public class ClassPathQuoteLoadingSystem implements QuoteLoadingService{
  private QuoteRepository quoteRepository;

  public ClassPathQuoteLoadingSystem(QuoteRepository quoteRepository) {
    this.quoteRepository = quoteRepository;
  }

  @Override
  public void loadQuotes() {
    try(InputStreamReader r = new InputStreamReader(getClass().getResourceAsStream("/week2-stocks.json"))){
      Quote[] quotes = new Gson().fromJson(r, Quote[].class);
      quoteRepository.saveAll(Arrays.asList(quotes));
    }catch (Exception e){
      throw new RuntimeException(e);
    }
  }
}
