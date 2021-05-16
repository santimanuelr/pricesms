package com.inditex.prices.app.service;

import com.google.common.collect.Lists;
import com.inditex.prices.app.domain.PriceDTO;
import com.inditex.prices.app.repository.PriceRepository;
import com.inditex.prices.app.web.PricesApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityResultHandler;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class PricesService implements PricesApiDelegate {

	@Autowired
	private PriceRepository priceRepository;

	@Override
	public ResponseEntity<List<PriceDTO>> listPrices(Integer limit) {
		PriceDTO priceDTO = new PriceDTO();
		priceDTO.setPrice(new BigDecimal(8));
		return ResponseEntity.ok(Collections.singletonList(priceDTO));
	}
}
