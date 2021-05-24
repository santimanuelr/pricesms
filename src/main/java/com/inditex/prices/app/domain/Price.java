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
	private Long priceListId;

	@Setter @Getter
	private Long productId;

	@Setter @Getter
	private Long priority;

	@Setter @Getter
	private BigDecimal price;

	@Setter @Getter
	private String currency;

	@Setter @Getter
	private Integer status;


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Price)) {
			return false;
		}
		return id != null && id.equals(((Price) o).id);
	}

	@Override
	public int hashCode() {
		// see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
		return getClass().hashCode();
	}

	// prettier-ignore
	@Override
	public String toString() {
		return "Price{" +
			"id=" + getId() +
			", startDate='" + getStartDate() + "'" +
			", endDate='" + getEndDate() + "'" +
			", productId=" + getProductId() +
			", priority=" + getPriority() +
			", price=" + getPrice() +
			", currency='" + getCurrency() + "'" +
			", status=" + getStatus() +
			"}";
	}

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
		priceDTO.setPriceListId(this.priceListId);
		priceDTO.setRate(new RateDTO());
		return priceDTO;
	}
}
