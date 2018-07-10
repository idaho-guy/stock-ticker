package com.solstice.stockticker.repository;

import com.solstice.stockticker.model.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {
}
