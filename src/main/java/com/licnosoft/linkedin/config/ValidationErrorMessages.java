package com.licnosoft.linkedin.config;

public class ValidationErrorMessages {
    public static final String CONFIRM_PASSWORD_MISMATCH = "Ο κωδικός και η επιβεβαίωση κωδικού δε ταυτίζονται.";
    public static final String USER_EMAIL_EXISTS = "Υφίσταται ήδη χρήστης με το συγκεκριμένο email.";
    public static final String BAD_CREDENTIALS = "Λάθος email ή κωδικός χρήστη.";

    private ValidationErrorMessages() {
        throw new IllegalStateException("Utility class");
    }
    // @NotBlank/@NotNull
	public static final String EREGISTRY_REGISTRATION_NUMBER_NOT_BLANK = "Ο αριθμός καταχώρησης eregistry δεν μπορεί να είναι κενός.";
    public static final String EREGISTRY_ID_NOT_BLANK = "Το eregistry ID δεν μπορεί να είναι κενό.";
    public static final String EREGISTRY_LICENSE_NOT_BLANK = "Το άδεια eregistry δεν μπορεί να είναι κενή.";
    public static final String TURNOVER_NOT_BLANK = "Ο τζίρος δεν μπορεί να είναι κενός.";
    public static final String DIMENSION_NOT_BLANK = "Η διάσταση δεν μπορεί να είναι κενή.";

    public static final String TITLE_NOT_BLANK = "Ο τίτλος δεν μπορεί να είναι κενός.";
    public static final String ESS_TEMPLATE_NOT_BLANK = "Το πρότυπο ΕΣΣ δεν μπορεί να είναι κενό.";
    public static final String YEAR_NOT_BLANK = "Το έτος δεν μπορεί να είναι κενό";
    public static final String REFERENCE_PERIOD_NOT_BLANK = "Η περίοδος αναφοράς δεν μπορεί να είναι κενή.";
    public static final String SUBMISSION_START_DATE_NOT_BLANK = "Η ημερομηνία έναρξης υποβολής δεν μπορεί να είναι κενή.";
    public static final String SUBMISSION_END_DATE_NOT_BLANK = "Η ημερομηνία λήξης υποβολής δεν μπορεί να είναι κενή.";
    public static final String END_DATE_NOT_BLANK = "Η ημερομηνία λήξης ΕΣΣ δεν μπορεί να είναι κενή.";
    public static final String STATUS_NOT_BLANK = "Η κατάσταση δεν μπορεί να είναι κενή.";
    public static final String CREATION_NOT_BLANK = "Η ημερομηνία δημιουργίας δεν μπορεί να είναι κενή.";
    public static final String ASSIGN_NOT_BLANK = "Η ημερομηνία ανάθεσης δεν μπορεί να είναι κενή.";
    public static final String MODIFICATION_NOT_BLANK = "Η ημερομηνία τελευταίας ενημέρωσης δεν μπορεί να είναι κενή.";
    public static final String CREATOR_NOT_BLANK = "Ο δημιουργός δεν μπορεί να είναι κενός.";
    public static final String QUESTIONNAIRE_NOT_BLANK = "Το ερωτηματολόγιο δεν μπορεί να είναι κενό.";
    public static final String SUBMISSION_PERIOD_NOT_BLANK = "Η περίοδος υποβολής δεν μπορεί να είναι κενή.";
    public static final String QUESTIONNAIRE_TEMPLATE_NOT_BLANK = "Το πρότυπο ερωτηματολόγιο δεν μπορεί να είναι κενό.";
    public static final String MARKET_NOT_BLANK = "Η αγορά δεν μπορεί να είναι κενή.";
    public static final String VERSION_NOT_BLANK = "Η έκδοση προτύπου ερωτηματολογίου δεν μπορεί να είναι κενή.";
    public static final String EXCEL_FILE_NOT_BLANK = "Το πρότυπο ερωτηματολόγιο (αρχείο Excel) δεν μπορεί να είναι κενό.";
    public static final String GUIDANCE_MANUAL_NOT_BLANK = "Το αρχείο οδηγιών συμπλήρωσης δεν μπορεί να είναι κενό.";
    public static final String ORGANIZATION_NOT_BLANK = "Ο φορέας δεν μπορεί να είναι κενός.";
    public static final String PROVIDER_NOT_BLANK = "Ο πάροχος δεν μπορεί να είναι κενός.";
    public static final String ESS_NOT_BLANK = "Η ΕΣΣ δεν μπορεί να είναι κενή.";
    public static final String FILE_NOT_BLANK = "Το αρχείο δεν μπορεί να είναι κενό.";
    public static final String FILE_TYPE_NOT_BLANK = "Ο τύπος αρχείου δεν μπορεί να είναι κενός.";
    public static final String UPLOADED_QUESTIONNAIRE_NOT_BLANK = "Το υποβαλλόμενο ερωτηματολόγιο δεν μπορεί να είναι κενό.";
    public static final String DESCRIPTION_NOT_BLANK = "Η περιγραφή δεν μπορεί να είναι κενή.";
    public static final String FACT_NOT_BLANK = "Το γεγονός δεν μπορεί να είναι κενό.";
    public static final String DIMENSION_FIELD_NAME_NOT_BLANK = "Το όνομα πεδίου της διάστασης δεν μπορεί να είναι κενό.";
    public static final String STAR_NOT_NULL = "Ο αστέρας δεν μπορεί να είναι κενός.";
    public static final String CHANNEL_NOT_BLANK = "Το κανάλι δεν μπορεί να είναι κενό.";
    public static final String MAPPING_ROW_NOT_BLANK = "Η γραμμή αντιστοίχισης δεν μπορεί να είναι κενή.";
    public static final String DIMENSION_TERM_NOT_BLANK = "Το μέλος διάστασης δεν μπορεί να είναι κενό.";
    public static final String NAME_NOT_BLANK = "Το όνομα δεν μπορεί να είναι κενό.";
    public static final String TYPE_NOT_BLANK = "Ο τύπος δεν μπορεί να είναι κενός.";
    public static final String TYPE_LENGTH_NOT_BLANK = "Το μήκος δεν μπορεί να είναι κενό.";
    public static final String MEASURE_NOT_BLANK = "Το μετρούμενο μέγεθος δεν μπορεί να είναι κενό.";
    public static final String EXCEL_CELL_COLUMN_NOT_BLANK = "Η στήλη κελιού Excel δε μπορεί να είναι κενή.";
    public static final String EXCEL_CELL_ROW_NOT_BLANK = "Η γραμμή κελιού Excel δε μπορεί να είναι κενή.";
    public static final String EXCEL_SHEET_NOT_BLANK = "Το φύλλο εργασίας Excel δε μπορεί να είναι κενό.";
    public static final String AUTHORITY_NOT_BLANK = "Το δικαίωμα δε μπορεί να είναι κενό";
    public static final String AUTHORITY_GROUP_NOT_BLANK = "H ομάδα δικαιωμάτων δε μπορεί να είναι κενή";
    public static final String EMAIL_NOT_BLANK = "Το email δε μπορεί να είναι κενό";
    public static final String CODE_NOT_BLANK = "Ο κωδικός δεν μπορεί να είναι κενός.";
    public static final String USER_NOT_BLANK = "Ο χρήστης δεν μπορεί να είναι κενός.";
    public static final String ROLE_NOT_BLANK = "Ο ρόλος δεν μπορεί να είναι κενός.";
    public static final String RIGHT_NOT_BLANK = "Το δικαίωμα δε μπορεί να είναι κενό";
    public static final String LASTNAME_NOT_BLANK = "Το επώνυμο δεν μπορεί να είναι κενό.";
    public static final String PHONE_NUMBER_NOT_BLANK = "Ο αριθμός τηλεφώνου δεν μπορεί να είναι κενός.";
    public static final String ORDERING_NOT_BLANK = "Η σειρά δε μπορεί να είναι κενή.";
    public static final String NOTIFICATION_NOT_BLANK = "Η ειδοποίηση δε μπορεί να είναι κενή.";
    public static final String ANNOUNCEMENT_NOT_BLANK = "Η ανακοίνωση δε μπορεί να είναι κενή.";
    public static final String URL_NOT_BLANK = "Το URL δε μπορεί να είναι κενό.";
    public static final String SUBMISSION_DATE_NOT_BLANK = "Η ημερομηνία υποβολής δεν μπορεί να είναι κενή.";
    public static final String VALUE_NOT_BLANK = "Η ιμή δεν μπορεί να είναι κενή.";
    public static final String ERROR_PARSING_VALUE = "Error parsing cell value";
    public static final String QUESTIONNAIRE_TEMPLATE_CANNOT_BE_DELETED = "Το πρότυπο ερωτηματολογίου δεν μπορεί να διαγραφεί γιατί χρησιμοποιείται από άλλα αντικείμενα.";
    public static final String IP_ADDRESS_NOT_BLANK = "Η διεύθυνση IP δεν μπορεί να είναι κενή.";
    public static final String SUBSYSTEM_NOT_BLANK = "Το υποσύστημα δεν μπορεί να είναι κενό.";

    // @Min
    public static final String YEAR_BEFORE_THAN_MIN = "Το έτος δε μπορεί να προηγείται του " + ValidationConstants.YEAR_MIN + ".";
    public static final String ORDERING_SMALLER_THAN_MIN = "Η σειρά δε μπορεί να είναι μικρότερη του " + ValidationConstants.ORDERING_MIN;

    // @Max
    public static final String YEAR_LATER_THAN_MAX = "Το έτος δε μπορεί να έπεται του " + ValidationConstants.YEAR_MAX + ".";

    // @Email
    public static final String INVALID_EMAIL = "Το email δεν είναι έγκυρο";

    // various
    public static final String MARKET_NOT_ALLOWED = "Η συγκεκριμένη αγορά δεν επιτρέπεται.";
    public static final String ORGANIZATION_NOT_ALLOWED = "Η συγκεκριμένος φορέας δεν επιτρέπεται.";

    // @File
    public static final String FILE_NOT_FOUND_OR_NO_ACCESS = "Το αρχείο δεν βρέθηκε στο σύστημα ή δεν έχετε δικαίωμα πρόσβασης";
    public static final String FILE_ALREADY_EXISTS = "Το αρχείο υπάρχει ήδη στο σύστημα";

    // Dimension
    public static final String DIMENSION_CANNOT_BE_DELETED = "Η διάσταση δεν μπορεί να διαγραφεί γιατί χρησιμοποιείται από άλλα αντικείμενα.";
    public static final String DIMENSION_NOT_FOUND = "Η διάσταση δε βρέθηκε στο σύστημα.";
    public static final String DIMENSION_CANNOT_BE_EDITED = "Η διάσταση δεν μπορεί να επεξεργαστεί γιατί υπάρχουν εκδόσεις ερωτηματολογίων μετά την τελευταία εκδοχή της διάστασης";

    // Questionnaire Templates
    public static final String QUESTIONNAIRE_TEMPLATE_CANNOT_BE_EDITED = "Δεν μπορείτε να κάνετε αλλαγές σε παλαιότερη έκδοση του ερωτηματολογίου.";
    public static final String QUESTIONNAIRE_TEMPLATE_BUSINESS_VALIDATION_ERRORS = "Οι παρακάτω συνθήκες ελέγχου δεν είναι σωστές: ";

    // Uploaded Questionnaires
    public static final String EXCEL_DOES_NOT_MATCH_DB = "Το αρχείο Excel δεν αντιστοιχεί στο σχήμα της βάσης δεδομένων";
    public static final String STAR_DOES_NOT_MATCH_EXCEL = " δεν αντιστοιχεί στο σχήμα της βάσης δεδομένων";
    public static final String UPLOADED_QUESTIONNAIRE_CHANGE_STATUS_ERROR_NOT_SUBMITTED = "Η κατάσταση δεν μπορεί να αλλάξει γιατί το ερωτηματολόγιο δεν έχει υποβληθεί.";
    public static final String UPLOADED_QUESTIONNAIRE_CHANGE_STATUS_ERROR_SUBMITTED = "Η κατάσταση δεν μπορεί να αλλάξει γιατί το ερωτηματολόγιο έχει υποβληθεί.";
    public static final String UPLOADED_QUESTIONNAIRE_CHANGE_STATUS_ERROR_GENERIC = "Η κατάσταση δεν μπορεί να αλλάξει.";
    public static final String UPLOADED_QUESTIONNAIRE_CANNOT_PARSE_VALUE = "Δεν μπορεί να αναγνωστεί η τιμή του κελιού: ";
    public static final String UPLOADED_QUESTIONNAIRE_CANNOT_READ_EXCEL = "Το αρχείο excel δεν μπορεί να διαβαστεί";
    public static final String UPLOADED_QUESTIONNAIRE_IS_FINALIZED = "Το ερωτηματολόγιο έχει ήδη οριστικοποιηθεί.";
    public static final String UPLOADED_QUESTIONNAIRE_NO_RECORDS = "Δεν βρέθηκαν τιμές στο αρχείο ερωτηματολογίου ή δεν έχει γίνει σωστά η αντιστοίχιση πεδίων.";

    // Ess
    public static final String ESS_TEMPLATE_UNIQUE_CONSTRAINT_EXCEPTION_MSG = "Ο συνδυασμός της αγοράς, του έτους και της περιόδου υποβολής πρέπει να είναι μοναδικός.";

    // Measure Mapppings
    public static final String MEASURE_MAPPING_CANNOT_BE_DELETED = "Η αντιστοίχιση πεδίου δεν μπορεί να διαγραφεί καθώς έχει γίνει εισαγωγή ερωτηματολογίου στο παρελθόν. Παρακαλώ προβείτε σε δημιουργία νέας έκδοσης.";
}
