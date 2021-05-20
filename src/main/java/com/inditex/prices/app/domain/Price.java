package com.inditex.prices.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "prices")
public class Price {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter @Getter
	private Long id;

	@Setter @Getter
	private Long brandId;

	@Setter @Getter
	private LocalDateTime startDate;

	@Setter @Getter
	private LocalDateTime endDate;

	@Setter @Getter
	private Long priceList;

	@Setter @Getter
	private Long productId;

	@Setter @Getter
	private Long priority;

	@Setter @Getter
	private BigDecimal price;

	@Setter @Getter
	private String currency;

	public PriceDTO getDTO() {
		PriceDTO priceDTO = new PriceDTO();
		priceDTO.setPrice(this.price);
		priceDTO.setCurrency(this.currency);
		priceDTO.setPriority(this.priority);
		priceDTO.setBrandId(this.brandId);
		ZoneOffset zoneOffSet = ZoneOffset.systemDefault().getRules().getOffset(this.startDate);
		priceDTO.setStartDate(this.startDate.atOffset(zoneOffSet));
		priceDTO.setEndDate(this.endDate.atOffset(zoneOffSet));
		priceDTO.setProductId(this.productId);
		return priceDTO;
	}
}
