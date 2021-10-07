package com.gerimedica.springboot.data.repository;

import com.gerimedica.springboot.data.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {

    public List<Observation> findByCode(String code);
}
