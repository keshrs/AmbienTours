package com.rikeshsubedi.ambientours;


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
    private String name;
    private int fileID;
    private double latitude;
    private double longitude;
    private double radius;

    public int getLocationSoundID() {
        return fileID;
    }


    PointOfInterest() {
        this.name = "The Moon";
        type = new HashSet<>();
        this.type.add(LocationType.NATURE);
        this.fileID = R.raw.twomoons;
    }
}