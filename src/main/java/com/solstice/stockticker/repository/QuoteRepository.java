package com.solstice.stockticker.repository;

import com.solstice.stockticker.dto.StockSummary;
import com.solstice.stockticker.model.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;

public interface QuoteRepository extends CrudRepository<Quote,Long> {

  @Query("select new com.solstice.stockticker.dto.StockSummary(max(q.price), min(q.price), sum(q.volume)) from Quote q " +
      "where symbol = :ticker and to_char(date, :format) = :date")
  StockSummary findSummaryByDate(@Param("ticker") String ticker, @Param("date") String date, @Param("format") String format);


  @Query("select count(*) from Quote q ")
  Long quoteCount();


  @Query("select q.price from Quote q where symbol = :ticker and date in (" +
      "select max(q.date) " +
      "from Quote q where symbol = :ticker and to_char(date, :format) = :date)")
  BigDecimal findClosingPriceByDate(@Param("ticker") String ticker, @Param("date") String date, @Param("format") String format);


}
