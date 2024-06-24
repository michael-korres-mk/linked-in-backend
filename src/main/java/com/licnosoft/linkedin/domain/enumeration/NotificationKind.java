package com.licnosoft.linkedin.domain.enumeration;

public enum NotificationKind {
    SUBMISSION_PERIOD_STARTED("Έναρξη περιόδου υποβολής ερωτηματολογίου"),
    SUBMISSION_PERIOD_ENDED("Λήξη μίας περιόδου υποβολής ερωτηματολογίου"),
    FAULTS_DISPATCHED("Αποστολή παρατηρήσεων μέσω ΕΕΤΤ"),
    ESS_UPDATED("Μεταβολή στοιχείων ΕΣΣ που έχει ήδη κοινοποιηθεί"),
    ESS_CLOSED("Κλείσιμο ΕΣΣ"),
    ANNOUNCEMENT_CREATED("Ανάρτηση νέας ανακοίνωσης"),
    ANNOUNCEMENT_UPDATED("Ενημέρωση ανακοίνωσης"),
    USER_REGISTRATION("Καταχώρηση νέου χρήστη");

    public final String label;
    NotificationKind(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
