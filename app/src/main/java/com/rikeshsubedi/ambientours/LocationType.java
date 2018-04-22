package com.rikeshsubedi.ambientours;

/**
 * Enum representing each type of location a user can choose to hear about.
 * @author Rikesh Subedi
 */
public enum LocationType {
    ART ("Art and Culture"),
    HISTORY ("History"),
    COMMERCE ("Restaurants and Commerce"),
    NATURE ("Nature"),
    ENTERTAINMENT ("Entertainment"),
    TOURIST ("Top Tourist Attractions");

    private final String title;

    LocationType(String title) {
        this.title = title;
    }
}
