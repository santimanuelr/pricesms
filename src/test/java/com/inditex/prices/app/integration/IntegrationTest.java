package com.inditex.prices.app.integration;

import com.inditex.prices.app.PricesmsApplication;
import com.inditex.prices.app.repository.PriceRepository;
import com.inditex.prices.app.web.PriceControllerTest;
import com.inditex.prices.app.web.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = PricesmsApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class IntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private PriceRepository repository;

	@Test
	public void testCaseApply1() throws Exception {

		mvc.perform(get("/prices/apply?applyDate=2020-06-14T10:00:00-03:00" +
			"&productId=35455&brandId=1").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.productId", is(35455)))
			.andExpect(jsonPath("$.price", is(35.50)));

	}

	@Test
	public void testCaseApply2() throws Exception {

		mvc.perform(get("/prices/apply?applyDate=2020-06-14T16:00:00-03:00" +
			"&productId=35455&brandId=1").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.productId", is(35455)))
			.andExpect(jsonPath("$.price", is(25.45)));

	}

	@Test
	public void testCaseApply3() throws Exception {

		mvc.perform(get("/prices/apply?applyDate=2020-06-14T21:00:00-03:00" +
			"&productId=35455&brandId=1").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.productId", is(35455)))
			.andExpect(jsonPath("$.price", is(35.50)));

	}

	@Test
	public void testCaseApply4() throws Exception {

		mvc.perform(get("/prices/apply?applyDate=2020-06-15T10:00:00-03:00" +
			"&productId=35455&brandId=1").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.productId", is(35455)))
			.andExpect(jsonPath("$.price", is(30.50)));

	}

	@Test
	public void testCaseApply5() throws Exception {

		mvc.perform(get("/prices/apply?applyDate=2020-06-16T21:00:00-03:00" +
			"&productId=35455&brandId=1").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.productId", is(35455)))
			.andExpect(jsonPath("$.price", is(38.95)));

	}

	@Test
	public void testApplyNotFound() throws Exception {

		mvc.perform(get("/prices/apply?applyDate=2020-06-16T21:00:00-03:00" +
			"&productId=9999&brandId=9999").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isNotFound());

	}

	@Test
	public void applyCaseBadRequest() throws Exception {

		mvc.perform(get("/prices/apply?applyDate=2020-06-16T21:00:00-03:00" +
			"&productId=-3&brandId=").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isBadRequest());

	}

	@Test
	public void applyCaseBadRequest2() throws Exception {

		mvc.perform(get("/prices/apply?applyDate=2020-06-16" +
			"&productId=9999&brandId=9999").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isBadRequest());

	}

	@Test
	public void applyCaseBadRequest3() throws Exception {

		mvc.perform(get("/prices/apply?applyDate=2020-06-16" +
			"&productId=-3&brandId=-2").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isBadRequest());

	}

	@Test
	public void postPriceOkAndGetPrices() throws Exception {

		mvc.perform(post("/prices").contentType(MediaType.APPLICATION_JSON)
			.content(TestUtil.convertObjectToJsonBytes(PriceControllerTest.createEntity())))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.price", is(1)));

		mvc.perform(get("/prices?limit=10").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(5)));

	}

}
