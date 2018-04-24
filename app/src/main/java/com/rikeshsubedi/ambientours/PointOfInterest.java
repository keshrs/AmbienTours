package com.rikeshsubedi.ambientours;


import android.location.Location;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Object representing a location of interest for a tourist.
 * @version 1.0
 * @author Rikesh Subedi (keshrs)
 */
public class PointOfInterest {

    // TODO: implement local variables containing location information and data
    private Set<LocationType> type;
    private OurPlaces place;
    private int fileID;

    private double latitude;
    private double longitude;
    private double radius;

    public int getLocationSoundID() {
        return fileID;
    }
    public Set<LocationType> getType() {
        return type;
    }


    PointOfInterest() {
        this.place = OurPlaces.MOON;
        this.type = new HashSet<>();
        this.type.add(LocationType.NATURE);
        this.fileID = place.getFileID();
    }

    PointOfInterest(OurPlaces place) {
        this.place = place;
        this.radius = place.getRadius();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
        this.type = place.getType();
        this.fileID = place.getFileID();
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
}