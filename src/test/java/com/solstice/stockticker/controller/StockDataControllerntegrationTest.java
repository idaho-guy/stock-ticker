package com.solstice.stockticker.controller;

import com.solstice.stockticker.dto.StockSummary;
import com.solstice.stockticker.repository.QuoteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockDataControllerntegrationTest {

  @Autowired
  private QuoteRepository quoteRepository;

  @LocalServerPort
  private int port;
  private RestTemplate restTemplate;


  @Before
  public void setUp() throws URISyntaxException {
    restTemplate = new RestTemplate();
  }

  @Test
  public void testLoadAndGetGet() throws Exception{
    assertLoadData();

    ResponseEntity<StockSummary> entity = restTemplate.getForEntity(new URI(String.format("http://localhost:%d/GOOG/2018-06-22", port)), StockSummary.class);
    StockSummary summary = entity.getBody();
    assertEquals(724223L, summary.getTotalVolume().longValue());
    assertEquals(new BigDecimal(1130.99).setScale(2,BigDecimal.ROUND_HALF_DOWN), summary.getHighPrice().setScale(2,BigDecimal.ROUND_HALF_DOWN));
    assertEquals(new BigDecimal(1120.01).setScale(2,BigDecimal.ROUND_HALF_DOWN), summary.getLowPrice().setScale(2).setScale(2,BigDecimal.ROUND_HALF_DOWN));
  }

  private void assertLoadData() throws URISyntaxException {
    assertEquals(0L, quoteRepository.quoteCount().longValue());
    restTemplate.postForLocation(new URI(String.format("http://localhost:%d/load",port)),null);
    assertEquals(7215L, quoteRepository.quoteCount().longValue());
  }
}
