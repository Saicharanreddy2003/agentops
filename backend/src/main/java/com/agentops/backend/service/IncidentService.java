
package com.agentops.backend.service;

import com.agentops.backend.model.Incident;
import com.agentops.backend.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public Optional<Incident> getIncidentById(Long id) {
        return incidentRepository.findById(id);
    }

    public Incident createIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    public Optional<Incident> updateIncident(Long id, Incident updatedIncident) {

        return incidentRepository.findById(id).map(existingIncident -> {

            existingIncident.setTitle(updatedIncident.getTitle());
            existingIncident.setDescription(updatedIncident.getDescription());
            existingIncident.setSeverity(updatedIncident.getSeverity());
            existingIncident.setStatus(updatedIncident.getStatus());

            return incidentRepository.save(existingIncident);
        });
    }

    public boolean deleteIncident(Long id) {

        if (!incidentRepository.existsById(id)) {
            return false;
        }

        incidentRepository.deleteById(id);
        return true;
    }

}
