package com.inditex.prices.app.web;

import com.inditex.prices.app.PricesmsApplication;
import com.inditex.prices.app.domain.Price;
import com.inditex.prices.app.domain.PriceDTO;
import com.inditex.prices.app.domain.RateDTO;
import com.inditex.prices.app.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = PricesmsApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {

	public static final String HOST = "http://localhost:";
	public static final String API_PRICES = "/prices";
	@LocalServerPort
	private int port;


	private static final LocalDateTime DEFAULT_START_DATE = LocalDateTime.now();
	private static final LocalDateTime UPDATED_START_DATE = LocalDateTime.now(ZoneId.systemDefault());

	private static final LocalDateTime DEFAULT_END_DATE = LocalDateTime.now();
	private static final LocalDateTime UPDATED_END_DATE = LocalDateTime.now(ZoneId.systemDefault());

	private static final Long DEFAULT_PRODUCT_ID = 1L;
	private static final Long UPDATED_PRODUCT_ID = 2L;

	private static final Long DEFAULT_PRIORITY = 1L;
	private static final Long UPDATED_PRIORITY = 2L;

	public static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
	private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

	private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
	private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

	private static final Integer DEFAULT_STATUS = 1;
	private static final Integer UPDATED_STATUS = 2;

	@Autowired
	private TestRestTemplate restTemplate;

	public static PriceDTO createEntity() {
		Price price = new Price();
		price.setBrandId(DEFAULT_PRODUCT_ID);
		price.setStartDate(DEFAULT_START_DATE);
		price.setEndDate(DEFAULT_END_DATE);
		price.setProductId(DEFAULT_PRODUCT_ID);
		price.setPriority(DEFAULT_PRIORITY);
		price.setPrice(DEFAULT_PRICE);
		price.setCurrency(DEFAULT_CURRENCY);
		price.setStatus(DEFAULT_STATUS);
		PriceDTO priceDTO = price.getDTO();
		RateDTO rateDTO = new RateDTO();
		rateDTO.setPriceListId(1l);
		priceDTO.setRate(rateDTO);
		return priceDTO;
	}

	@Test
	public void postPriceOk() throws Exception {

		PriceDTO priceDTO = createEntity();
		ResponseEntity<PriceDTO> response = restTemplate.postForEntity(
			new URL(HOST + port + API_PRICES).toString(), priceDTO, PriceDTO.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}

	@Test
	public void badRequestPriceCreationTest() throws Exception {
		PriceDTO priceDTO = createEntity();
		priceDTO.setPrice(null);
		ResponseEntity<PriceDTO> postForEntity = restTemplate.postForEntity(
			new URL(HOST + port + API_PRICES).toString(), priceDTO,PriceDTO.class);
		assertEquals(HttpStatus.BAD_REQUEST, postForEntity.getStatusCode());
	}

	@Test
	public void getNotFoundPrices() throws Exception {

		ResponseEntity<PriceDTO> getResponse = restTemplate.getForEntity(
			new URL(HOST + port + API_PRICES + "/" + 999).toString(), PriceDTO.class);
		assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());

	}

	@Test
	public void getPrices() throws Exception {

		ResponseEntity<PriceDTO[]> getResponse = restTemplate.getForEntity(
			new URL(HOST + port + API_PRICES + "?limit=" + 2).toString(), PriceDTO[].class);
		assertEquals(HttpStatus.OK, getResponse.getStatusCode());

	}

}
