package com.solstice.stockticker.repository;

import com.solstice.stockticker.dto.StockSummaryDto;
import com.solstice.stockticker.model.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface QuoteRepository extends CrudRepository<Quote,Long> {

  @Query("select new com.solstice.stockticker.dto.StockSummaryDto(max(q.price), min(q.price), sum(q.volume)) from Quote q inner join q.stock as stock " +
      "where stock.symbol = :ticker and to_char(date, :format) = :date")
  StockSummaryDto findSummaryByDate(@Param("ticker") String ticker, @Param("date") String date, @Param("format") String format);


  @Query("select count(*) from Quote q ")
  Long quoteCount();


  @Query("select q.price from Quote q inner join q.stock as stock where stock.symbol = :ticker and date in (" +
      "select max(q.date) " +
      "from Quote q inner join q.stock as stock where stock.symbol = :ticker and to_char(date, :format) = :date)")
  BigDecimal findClosingPriceByDate(@Param("ticker") String ticker, @Param("date") String date, @Param("format") String format);


}
