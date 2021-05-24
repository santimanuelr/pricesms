package com.inditex.prices.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Rate.
 */
@Entity
@Table(name = "rate")
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter @Getter
    private Long id;

    @Column(name = "discount", precision = 21, scale = 2)
	@Setter @Getter
    private BigDecimal discount;

    @Column(name = "extra_charge", precision = 21, scale = 2)
	@Setter @Getter
    private BigDecimal extraCharge;

    @Column(name = "name")
	@Setter @Getter
    private String name;

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rate)) {
            return false;
        }
        return id != null && id.equals(((Rate) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Rate{" +
            "id=" + getId() +
            ", discount=" + getDiscount() +
            ", extraCharge=" + getExtraCharge() +
            ", name='" + getName() + "'" +
            "}";
    }

	public RateDTO getDTO() {
    	RateDTO rateDTO = new RateDTO();
    	rateDTO.setPriceListId(this.id);
    	rateDTO.setDiscount(this.discount);
    	rateDTO.setExtraCharge(this.extraCharge);
    	rateDTO.setName(this.name);
    	return rateDTO;
	}
}
