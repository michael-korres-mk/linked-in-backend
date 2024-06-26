package com.licnosoft.linkedin.domain.enumeration;

public enum NotificationKind {
    CONNECTION_REQUEST("Αίτημα Σύνδεσης"),
    EXPRESSION_OF_INTEREST("Σημείωση Ενδιαφέροντος");

    public final String label;
    NotificationKind(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
