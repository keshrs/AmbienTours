package com.rikeshsubedi.ambientours;

/**
 * Object representing a location of interest for a tourist.
 * @version 1.0
 * @author Rikesh Subedi
 */
public class PointOfInterest {

    // TODO: implement local variables containing location information and data
    LocationType type;
    String name;
    private int fileID;

    public int getLocationSoundID() {
        return fileID;
    }


    PointOfInterest() {
        this.name = "The Moon";
        this.type = LocationType.NATURE;
        this.fileID = R.raw.twomoons;
    }
}
