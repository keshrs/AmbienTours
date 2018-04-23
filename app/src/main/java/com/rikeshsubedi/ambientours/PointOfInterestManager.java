package com.rikeshsubedi.ambientours;


import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author Rikesh Subedi
 * @version 1.0
 */
public class PointOfInterestManager {
    static private PriorityQueue<PointOfInterest> primedPOIs;
    static private HashSet<PointOfInterest> visitedPOIs = new HashSet<>();

    private LinkedList<String> requestQueue;    // For use with Google Places API

    private static final String PLACES_API_KEY = "AIzaSyCvaoDH7cYyQkNTpFasZ5xQufh-DmEQ-jA";

    private Location location;
    private double latitude;
    private double longitude;

    private final static int searchRadius = 50;

    /* These variables contain the state of the tour parameters */
    private boolean isArt = false;
    private boolean isEntertainment = false;
    private boolean isHistory = false;
    private boolean isNature = false;
    private boolean isCommerce = false;
    private boolean isTourist = false;

    public PointOfInterestManager(Location location, Bundle extras) {
        if (extras != null) {
            isArt = extras.getBoolean("art");
            isEntertainment = extras.getBoolean("entertainment");
            isHistory = extras.getBoolean("history");
            isNature = extras.getBoolean("nature");
            isCommerce = extras.getBoolean("commerce");
            isTourist = extras.getBoolean("tourist");
        }
        update(location);
    }

    /**
     * No-param handler for update function.
     */
    public void update() {
        update(location);
    }

    /**
     * Updates location information and query list.
     * @param location new location for the POI Manager.
     */
    public void update(Location location) {
        this.location = location;
        latitude = location.getLatitude();
        longitude = location.getLongitude();


        /* In current implementation, Places API is not used and neither is the request queue */
        buildRequestQueue();
    }

    /**
     * Builds a single query for the Google Places API
     * @param type type of place to query for.
     * @return formatted String representation of the query.
     */
    private String buildQuery(String type) {
        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location=").append(latitude).append(",").append(longitude);
        sb.append("&radius=" + searchRadius);
        sb.append("&types=").append(type);
        sb.append("&sensor=true");
        sb.append("&key=" + PLACES_API_KEY);

        Log.d("Map", "api: " + sb.toString());

        return sb.toString();
    }

    /**
     * Builds list of queries for Google Places API.
     */
    private void buildRequestQueue() {
        requestQueue = new LinkedList<>();
        if (isArt) {
            requestQueue.add(buildQuery("art_gallery"));
            requestQueue.add(buildQuery("embassy"));
            requestQueue.add(buildQuery("hindu_temple"));
            requestQueue.add(buildQuery("mosque"));
            requestQueue.add(buildQuery("museum"));
            requestQueue.add(buildQuery("painter"));
            requestQueue.add(buildQuery("synagogue"));
        }
        if (isEntertainment) {
            requestQueue.add(buildQuery("amusement_park"));
            requestQueue.add(buildQuery("aquarium"));
            requestQueue.add(buildQuery("beauty_salon"));
            requestQueue.add(buildQuery("bowling_alley"));
            requestQueue.add(buildQuery("casino"));
            requestQueue.add(buildQuery("library"));
            requestQueue.add(buildQuery("museum"));
            requestQueue.add(buildQuery("night_club"));
            requestQueue.add(buildQuery("art_gallery"));
            requestQueue.add(buildQuery("spa"));
            requestQueue.add(buildQuery("stadium"));
            requestQueue.add(buildQuery("zoo"));
        }
        if (isHistory) {
            requestQueue.add(buildQuery("church"));
            requestQueue.add(buildQuery("city_hall"));
            requestQueue.add(buildQuery("courthouse"));
            requestQueue.add(buildQuery("fire_station"));
            requestQueue.add(buildQuery("local_government_office"));
            requestQueue.add(buildQuery("school"));
        }
        if (isCommerce) {
            requestQueue.add(buildQuery("atm"));
            requestQueue.add(buildQuery("bakery"));
            requestQueue.add(buildQuery("bank"));
            requestQueue.add(buildQuery("bar"));
            requestQueue.add(buildQuery("bicycle_store"));
            requestQueue.add(buildQuery("book_store"));
            requestQueue.add(buildQuery("cafe"));
            requestQueue.add(buildQuery("clothing_store"));
            requestQueue.add(buildQuery("convenience_store"));
            requestQueue.add(buildQuery("department_store"));
            requestQueue.add(buildQuery("electronics_store"));
            requestQueue.add(buildQuery("florist"));
            requestQueue.add(buildQuery("jewelry_store"));
            requestQueue.add(buildQuery("restaurant"));
            requestQueue.add(buildQuery("shopping_mall"));
            requestQueue.add(buildQuery("supermarket"));
        }
        if (isNature) {
            requestQueue.add(buildQuery("campground"));
            requestQueue.add(buildQuery("cemetery"));
            requestQueue.add(buildQuery("park"));
            requestQueue.add(buildQuery("art_gallery"));
            requestQueue.add(buildQuery("art_gallery"));
        }
        if (isTourist) {
            requestQueue.add(buildQuery("airport"));
            requestQueue.add(buildQuery("bus_station"));
            requestQueue.add(buildQuery("car_rental"));
            requestQueue.add(buildQuery("gas_station"));
            requestQueue.add(buildQuery("hospital"));
            requestQueue.add(buildQuery("lodging"));
            requestQueue.add(buildQuery("subway_station"));
            requestQueue.add(buildQuery("travel_agency"));
            requestQueue.add(buildQuery("transit_station"));
        }
    }


    public static PriorityQueue<PointOfInterest> getPrimedPOIs() {
        return primedPOIs;
    }

    public static HashSet<PointOfInterest> getVisitedPOIs() {
        return  visitedPOIs;
    }

}
