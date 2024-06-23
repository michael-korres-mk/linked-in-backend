package com.licnosoft.linkedin.config;

public class Constants {
    public static final String APP_NAME = "Fullcalendar";
    public static final String UPLOAD_FOLDER = "uploads/";
    public static final String FILES_ENDPOINT = "files/";
    public static final String APP_TIMEZONE = "Europe/Athens";
    public static final String ADMIN_EMAIL = "";

    public static final String MEETING_SLOT_OVERLAP_ERROR = "Η καταχώρηση διαθεσιμότητας δεν μπορεί να ολοκληρωθεί, το διάστημα διαθεσιμότητας υπερκαλύπτετε από άλλη καταχωρημένη διαθεσιμότητα.";
    public static final String MEETING_SLOT_START_AFTER_END_ERROR = "Η καταχώρηση διαθεσιμότητας δεν μπορεί να ολοκληρωθεί, η ημερομηνία λήξης δεν μπορεί να προηγείται της ημερομηνία έναρξης.";
    public static final String MEETING_SLOT_START_END_PAST_ERROR = "Η καταχώρηση διαθεσιμότητας δεν μπορεί να ολοκληρωθεί, η ημερομηνία έναρξης/λήξης δεν μπορεί να είναι παρελθοντική.";

	private Constants() {
    }
}
