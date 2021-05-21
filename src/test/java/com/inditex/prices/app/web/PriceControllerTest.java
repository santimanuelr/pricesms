package com.inditex.prices.app.web;

import com.inditex.prices.app.PricesmsApplication;
import com.inditex.prices.app.domain.PriceDTO;
import com.inditex.prices.app.repository.PriceRepository;
import com.inditex.prices.app.service.PricesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = PricesmsApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/data.sql"})
public class PriceControllerTest {

	public static final String HOST = "http://localhost:";
	public static final String API_PRICES = "/api/prices";
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PriceRepository priceRepository;

//	@Test
//	public void postPriceOk() throws Exception {
//
//		Customer customer = new Customer("Name", "Last Name",
//			"Sierra vieja 123", "Lima", "customer@mail.com");
//		ResponseEntity<Long> response = restTemplate.postForEntity(
//			new URL(HOST + port + API_CUSTOMERS).toString(), customer, Long.class);
//		assertEquals(HttpStatus.CREATED, response.getStatusCode());
//
//	}

	@Test
	public void getNotFoundPrices() throws Exception {

		ResponseEntity<PriceDTO> getResponse = restTemplate.getForEntity(
			new URL(HOST + port + API_PRICES + "/" + 999).toString(), PriceDTO.class);
		assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());

	}

	@Test
	public void getNotFoundPrices2() throws Exception {

		ResponseEntity<PriceDTO> getResponse = restTemplate.getForEntity(
			new URL(HOST + port + API_PRICES + "/" + 1).toString(), PriceDTO.class);
		assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());

	}

	@Test
	public void getTest1() throws Exception {

		ResponseEntity<PriceDTO> getResponse = restTemplate.getForEntity(
			new URL(HOST + port + API_PRICES + "/apply?applyDate=" +
				"2020-06-14T00:00:00-03:00&productId=35455&brandId=1").toString(), PriceDTO.class);
		assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());

	}

}
