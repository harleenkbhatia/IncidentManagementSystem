package com.api.incidentManagement.service;


import com.api.incidentManagement.model.Incident;
import com.api.incidentManagement.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {
    @Autowired
    private IncidentRepository incidentRepository;

    public Incident saveIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    public List<Incident> findByUserId(Long userId) {
        return incidentRepository.findByUserId(userId);
    }

    public Optional<Incident> findByIncidentId(String incidentId) {
        return incidentRepository.findByIncidentId(incidentId);
    }


    public void deleteIncident(Incident incident) {
        incidentRepository.deleteById(incident.getId());
    }
}
