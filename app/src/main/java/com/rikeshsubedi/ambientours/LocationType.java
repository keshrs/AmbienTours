package com.rikeshsubedi.ambientours;

/**
 * Enum representing each type of location a user can choose to hear about.
 * @author Rikesh Subedi
 */
public enum LocationType {
    ART ("artNotification.wav"),
    HISTORY ("historyNotification.wav"),
    COMMERCE ("commerceNotification.wav"),
    NATURE ("natureNotification.wav"),
    ENTERTAINMENT ("entertainmentNotification.wav"),
    TOURIST ("touristNotification.wav");

    private final String filename;

    LocationType(String filename) {
        this.filename = filename;
    }
}
