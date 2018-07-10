package com.solstice.stockticker.service;

import com.solstice.stockticker.controller.StockSummaryParams;
import com.solstice.stockticker.dto.StockSummary;
import com.solstice.stockticker.exception.NotFoundException;
import com.solstice.stockticker.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuoteService {
  private QuoteRepository repository;
  private final Validator validator;


  public QuoteService(QuoteRepository repository, ValidatorFactory validatorFactory) {
    this.repository = repository;
    validator = validatorFactory.getValidator();
  }

  public StockSummary findSummary(String ticker, String date) {
    Set<ConstraintViolation<StockSummaryParams>> possibleErrors = validator.validate(new StockSummaryParams(ticker, date));
    if(possibleErrors.size() > 0){
      throw new ValidationException(possibleErrors.stream().map(e -> e.getMessage()).collect(Collectors.joining("\n")));
    }
    StockSummary summary = repository.findSummary(ticker, date);
    if(summary == null || summary.getTotalVolume() == null){
      throw new NotFoundException(String.format("No information found for ticker:date %s:%s",ticker,date));
    }
    return summary;
  }

  public void verifyEmpty(){
    if(repository.quoteCount() > 0L){
      throw new ValidationException("Database already loaded");
    }
  }
}
