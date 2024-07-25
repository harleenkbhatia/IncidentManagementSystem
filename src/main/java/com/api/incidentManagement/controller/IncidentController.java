package com.api.incidentManagement.controller;

import com.api.incidentManagement.model.Incident;
import com.api.incidentManagement.model.User;
import com.api.incidentManagement.service.IncidentService;
import com.api.incidentManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    @Autowired
    private IncidentService incidentService;
    @Autowired
    private UserService userService;

    @PostMapping("/addIncident")
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident, @RequestParam Long userId) {
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            incident.setUser(user.get());
            incident.setIncidentId(generateIncidentId()); // Implement generateIncidentId() method to generate unique incident IDs
            Incident savedIncident = incidentService.saveIncident(incident);
            return ResponseEntity.ok(savedIncident);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Incident>> getIncidentsByUser(@PathVariable Long userId) {
        List<Incident> incidents = incidentService.findByUserId(userId);
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/search")
    public ResponseEntity<Incident> getIncidentByIncidentId(@RequestParam String incidentId) {
        Optional<Incident> incident = incidentService.findByIncidentId(incidentId);
        return incident.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("update")
    public ResponseEntity<Incident> updateIncident(@RequestBody Incident incident) {
        Optional<Incident> existingIncident = incidentService.findByIncidentId(incident.getIncidentId());
       if ("Closed".equals(existingIncident.get().getStatus())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Incident is closed
            }
            Incident updatedIncident = existingIncident.get();
            updatedIncident.setEnterpriseOrGovernment(incident.getEnterpriseOrGovernment());
            updatedIncident.setIncidentDetails(incident.getIncidentDetails());
            updatedIncident.setPriority(incident.getPriority());
            updatedIncident.setStatus(incident.getStatus());
            incidentService.saveIncident(updatedIncident);
            return ResponseEntity.ok(updatedIncident);

    }

    @DeleteMapping("/delete/{incidentId}")
    public ResponseEntity<Void> deleteIncident(@PathVariable String incidentId) {
        Optional<Incident> existingIncident = incidentService.findByIncidentId(incidentId);
        if (existingIncident.isPresent()) {
            incidentService.deleteIncident(existingIncident.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String generateIncidentId() {
        String year = String.valueOf(LocalDateTime.now().getYear());
        String randomDigits = String.format("%05d", new Random().nextInt(100000));
        return "RMG" + randomDigits + year;
    }
}
