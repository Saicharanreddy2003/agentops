
package com.agentops.backend.controller;

import com.agentops.backend.model.Incident;
import com.agentops.backend.service.IncidentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long id) {
        return incidentService.getIncidentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Incident> createIncident(@Valid @RequestBody Incident incident) {
        Incident createdIncident = incidentService.createIncident(incident);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdIncident);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(
            @PathVariable Long id,
            @Valid @RequestBody Incident incident) {

        return incidentService.updateIncident(id, incident)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {

        boolean deleted = incidentService.deleteIncident(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
