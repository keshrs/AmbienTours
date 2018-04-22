package com.rikeshsubedi.ambientours;

import android.media.MediaPlayer;

/**
 * Object representing a location of interest for a tourist.
 * @version 1.0
 * @author Rikesh Subedi
 */
public class PointOfInterest {

    // TODO: implement local variables containing location information and data
    String name;
    private int fileID;
    MediaPlayer playback;

    public int getLocationSoundID() {
        return fileID;
    }


    PointOfInterest() {
        this.name = "The Moon";
        this.fileID = R.raw.twomoons;
    }
}
