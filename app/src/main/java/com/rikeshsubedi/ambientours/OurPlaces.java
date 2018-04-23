package com.rikeshsubedi.ambientours;

/**
 * Enum representing each location currently implemented.
 * @author Rikesh Subedi
 */
public enum OurPlaces {
    FIFTH_STREET_BRIDGE (R.raw.fifthstbridge),
    TECHNOLOGY_SQUARE (R.raw.techsquare),
    TSRB (R.raw.tsrb),
    BARNES_AND_NOBLE (R.raw.barnesandnoble),
    SCHELLER (R.raw.scheller),
    MOON (R.raw.twomoons);

    private final int fileID;

    OurPlaces(int filename) {
        this.fileID = filename;
    }

    public int getFileID() {
        return fileID;
    }
}
