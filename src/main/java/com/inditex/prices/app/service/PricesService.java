package com.inditex.prices.app.service;

import com.inditex.prices.app.domain.Price;
import com.inditex.prices.app.domain.PriceDTO;
import com.inditex.prices.app.repository.PriceRepository;
import com.inditex.prices.app.web.PricesApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

	@Override
	public ResponseEntity<PriceDTO> showPriceById(String priceId) {
		return priceRepository.findById(Long.valueOf(priceId))
			.map(respose -> ResponseEntity.ok(respose.getDTO()))
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Override
	public ResponseEntity<PriceDTO> findPriceByProductAndBrandAndStartDate(OffsetDateTime applyDate,
																		   Long productId, Long brandId) {
		return priceRepository.findPriceWitchApply(productId, brandId, applyDate.toLocalDateTime(), PageRequest.of(0,1))
			.stream()
			.findFirst()
			.map(p -> ResponseEntity.ok(p.getDTO()))
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
