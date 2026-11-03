
package com.agentops.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/incidents")
public class IncidentController {

    @GetMapping
    public String getIncidents() {
        return "AgentOps Incident Management API is running!";
    }
}
