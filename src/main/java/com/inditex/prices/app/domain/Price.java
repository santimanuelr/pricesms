package com.inditex.prices.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
	private Long pricessl;

	@Setter @Getter
	private Long productId;

	@Setter @Getter
	private Long priority;

	@Setter @Getter
	private BigDecimal price;

	@Setter @Getter
	private String currency;

}
