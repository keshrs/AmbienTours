package com.rikeshsubedi.ambientours;

import android.location.Location;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enum representing each location currently implemented.
 * @author Rikesh Subedi
 */
public enum OurPlaces {
    FIFTH_STREET_BRIDGE (R.raw.fifthstbridge, 33.776862, -84.390851, 30,
            new HashSet<>(Arrays.asList(LocationType.TOURIST, LocationType.NATURE))),
    TECHNOLOGY_SQUARE (R.raw.techsquare, 33.776837, -84.388950, 30,
            new HashSet<>(Arrays.asList(LocationType.TOURIST, LocationType.COMMERCE))),
    TSRB (R.raw.tsrb, 33.777356, -84.389988, 30,
            new HashSet<>(Arrays.asList(LocationType.TOURIST, LocationType.HISTORY))),
    BARNES_AND_NOBLE (R.raw.barnesandnoble, 33.776353, -84.388483, 30,
            new HashSet<>(Arrays.asList(LocationType.TOURIST, LocationType.COMMERCE))),
    SCHELLER (R.raw.scheller, 33.776280, -84.387774, 30,
            new HashSet<>(Arrays.asList(LocationType.TOURIST))),
    MOON (R.raw.twomoons, 0, 0, 0, new HashSet<LocationType>());

    private final int fileID;
    private final double latitude;
    private final double longitude;
    private final int radius;
    private final Set<LocationType> type;

    /**
     * Constructor giving each enum a file id, latitude, longitude, and detection radius.
     * @param filename id for the sound file associated with this location.
     * @param latitude of this place.
     * @param longitude of this place.
     * @param radius of detection for user interaction, in meters.
     */
    OurPlaces(int filename, double latitude, double longitude, int radius, HashSet<LocationType> type) {
        this.fileID = filename;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.type = type;
    }

    public boolean isWithinRange(Location location) {
        return distanceFromLocation(location) <= radius;
    }

    /**
     * Calculates the distance between two LatLon coordinates.
     * @param location of other place to calculate distance from.
     * @return distance between the two points.
     */
    public double distanceFromLocation(Location location) {
        int rEarth = 6371;  // km
        double dLat = degreeToRad(location.getLatitude() - latitude);
        double dLon = degreeToRad(location.getLongitude() - longitude);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(degreeToRad(latitude))
                * Math.cos(degreeToRad(location.getLatitude())) * Math.sin(dLon/2)
                * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = rEarth * c;   // in km

        return dist * 1000; // now in meters
    }

    private double degreeToRad(double number) {
        return number * (Math.PI / 180);
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

    public Set<LocationType> getType() {
        return type;
    }
}
