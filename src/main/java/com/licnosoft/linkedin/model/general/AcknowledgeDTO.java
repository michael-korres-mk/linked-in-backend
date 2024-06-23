package com.licnosoft.linkedin.model.general;

import java.io.Serializable;

public class AcknowledgeDTO implements Serializable {

    private Long eventId;
    private Long studentId;
    private String guardianComments;
    private String declaration;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getGuardianComments() {
        return guardianComments;
    }

    public void setGuardianComments(String guardianComments) {
        this.guardianComments = guardianComments;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }
}
