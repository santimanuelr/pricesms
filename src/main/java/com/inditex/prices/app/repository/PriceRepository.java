package com.inditex.prices.app.repository;

import com.inditex.prices.app.domain.Price;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>  {

	@Query(value = "SELECT p FROM Price p WHERE p.productId = :productId and p.brandId = :brandId and p.startDate <= :date and p.endDate >= :date order by p.priority DESC")
	List<Price> findPriceWitchApply(@Param("productId") Long productId,
							 @Param("brandId") Long brandId,
							 @Param("date") LocalDateTime date, Pageable pageable);

}
