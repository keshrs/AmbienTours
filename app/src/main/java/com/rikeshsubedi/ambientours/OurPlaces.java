package com.rikeshsubedi.ambientours;

/**
 * Enum representing each location currently implemented.
 * @author Rikesh Subedi
 */
public enum OurPlaces {
    FIFTH_STREET_BRIDGE (R.raw.fifthstbridge, 33.776862, -84.390851, 30),
    TECHNOLOGY_SQUARE (R.raw.techsquare, 33.776837, -84.388950, 30),
    TSRB (R.raw.tsrb, 33.777356, -84.389988, 30),
    BARNES_AND_NOBLE (R.raw.barnesandnoble, 33.776353, -84.388483, 30),
    SCHELLER (R.raw.scheller, 33.776280, -84.387774, 30),
    MOON (R.raw.twomoons, 0, 0, 0);

    private final int fileID;
    private final double latitude;
    private final double longitude;
    private final int radius;

    /**
     * Constructor giving each enum a file id, latitude, longitude, and detection radius.
     * @param filename id for the sound file associated with this location.
     * @param latitude of this place.
     * @param longitude of this place.
     * @param radius of detection for user interaction, in meters.
     */
    OurPlaces(int filename, double latitude, double longitude, int radius) {
        this.fileID = filename;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public int getFileID() {
        return fileID;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getRadius() {
        return radius;
    }
}
