package com.solstice.stockticker.service;

import com.solstice.stockticker.model.Quote;
import com.solstice.stockticker.repository.QuoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
@Service
public class RestQuoteLoadingSystem implements QuoteLoadingService {

  private RestTemplate restTemplate;
  private QuoteRepository quoteRepository;

  public RestQuoteLoadingSystem(QuoteRepository quoteRepository) {
    this.restTemplate = new RestTemplate();
    this.quoteRepository = quoteRepository;
  }

  @Override
  public void loadQuotes() {
    try {
      ResponseEntity<Quote[]> quotes = restTemplate.getForEntity(new URI("https://bootcamp-training-files.cfapps.io/week2/week2-stocks.json"), Quote[].class);
      quoteRepository.saveAll(Arrays.asList(quotes.getBody()));
    } catch (Exception e) {
      throw new RuntimeException("Unable to load quotes to database", e);
    }
  }
}
