package com.josephrodriguez.learning.springboot.data.repository;

import com.josephrodriguez.learning.springboot.data.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, String> {

}
