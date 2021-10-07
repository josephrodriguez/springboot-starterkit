package com.gerimedica.springboot.data.repository;

import com.gerimedica.springboot.data.entity.Observation;
import org.springframework.data.repository.CrudRepository;

public interface ObservationRepository extends CrudRepository<Observation, Long> {
}
