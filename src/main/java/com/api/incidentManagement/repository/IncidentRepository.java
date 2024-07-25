package com.api.incidentManagement.repository;

import com.api.incidentManagement.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findByUserId(Long userId);
    Optional<Incident> findByIncidentId(String incidentId);
}
