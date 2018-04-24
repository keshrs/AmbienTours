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

    private double radius;

    public int getLocationSoundID() {
        return fileID;
    }


    PointOfInterest() {
        this.place = OurPlaces.MOON;
        this.type = new HashSet<>();
        this.type.add(LocationType.NATURE);
        this.fileID = place.getFileID();
    }

    PointOfInterest(OurPlaces place, LocationType... type) {
        this.place = place;
        this.radius = place.getRadius();
        this.type = new HashSet<>();
        this.type.addAll(Arrays.asList(type));
        this.fileID = place.getFileID();
    }
}