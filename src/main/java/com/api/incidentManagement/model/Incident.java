package com.api.incidentManagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String incidentId;
    private String reporterName;
    private String incidentDetails;
    private String enterpriseOrGovernment;
    private LocalDateTime incidentReportedDateTime;
    private String priority;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }
    public String getEnterpriseOrGovernment() {
        return enterpriseOrGovernment;
    }

    public void setEnterpriseOrGovernment(String enterpriseOrGovernment) {
        this.enterpriseOrGovernment = enterpriseOrGovernment;
    }
    public String getIncidentDetails() {
        return incidentDetails;
    }

    public void setIncidentDetails(String incidentDetails) {
        this.incidentDetails = incidentDetails;
    }

    public LocalDateTime getIncidentReportedDateTime() {
        return incidentReportedDateTime;
    }

    public void setIncidentReportedDateTime(LocalDateTime incidentReportedDateTime) {
        this.incidentReportedDateTime = incidentReportedDateTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
