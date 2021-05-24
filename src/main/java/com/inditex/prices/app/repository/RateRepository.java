package com.inditex.prices.app.repository;

import com.inditex.prices.app.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Rate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {}
