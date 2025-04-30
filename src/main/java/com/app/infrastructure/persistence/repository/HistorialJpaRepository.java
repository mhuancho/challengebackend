package com.app.infrastructure.persistence.repository;

import com.app.infrastructure.persistence.entity.Historial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialJpaRepository extends JpaRepository<Historial, Long> {

    Page<Historial> findAll(Pageable pageable);
}