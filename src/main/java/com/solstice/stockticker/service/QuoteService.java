package com.solstice.stockticker.service;

import com.solstice.stockticker.controller.StockSummaryDateParams;
import com.solstice.stockticker.controller.StockSummaryMonthParams;
import com.solstice.stockticker.controller.StockSummaryParams;
import com.solstice.stockticker.dto.StockSummaryDto;
import com.solstice.stockticker.exception.NotFoundException;
import com.solstice.stockticker.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class QuoteService {
  private QuoteRepository repository;
  private final Validator validator;

  private static final String DATE_FORMAT = "yyyy-MM-dd";
  private static final String MONTH_FORMAT = "yyyy-MM";


  public QuoteService(QuoteRepository repository, ValidatorFactory validatorFactory) {
    this.repository = repository;
    validator = validatorFactory.getValidator();
  }

  public StockSummaryDto findSummaryByDate(String ticker, String date) {
    return getStockSummary(ticker, date, DATE_FORMAT,() -> new StockSummaryDateParams(ticker, date));
  }

  public StockSummaryDto findSummaryByMonth(String ticker, String month) {
    return getStockSummary(ticker, month, MONTH_FORMAT,() -> new StockSummaryMonthParams(ticker, month));
  }

  private StockSummaryDto getStockSummary(String ticker, String month, String format, Supplier<StockSummaryParams> supplier) {
    validateSummary(supplier);
    StockSummaryDto summary = repository.findSummaryByDate(ticker, month, format);
    if(summary == null || summary.getTotalVolume() == null){
      throw new NotFoundException(String.format("No information found for ticker:date %s:%s",ticker,month));
    }
    summary.setClosingPrice(repository.findClosingPriceByDate(ticker,month, format));
    return summary;
  }

  private <T extends StockSummaryParams> void validateSummary(Supplier<T> supplier){
    Set<ConstraintViolation<StockSummaryParams>> possibleErrors = validator.validate(supplier.get());
    if(possibleErrors.size() > 0){
      throw new ValidationException(possibleErrors.stream().map(e -> e.getMessage()).collect(Collectors.joining("\n")));
    }
  }

  public void verifyEmpty(){
    if(repository.quoteCount() > 0L){
      throw new ValidationException("Database already loaded");
    }
  }
}
