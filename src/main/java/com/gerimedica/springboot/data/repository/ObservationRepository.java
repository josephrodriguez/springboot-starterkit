package com.gerimedica.springboot.data.repository;

import com.gerimedica.springboot.data.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {
}
