package com.example.demo.common;

import com.example.demo.repository.entity.Location;

/**
 * Utility class to calculate distance between two location
 */
public class Utility {

    /**
     * @param a
     * @param b
     * @return distance
     */
    public static double distanceBetween(Location a, Location b) {
        double longDif = a.getLng() - b.getLng();

        double distance =
                Math.sin(deg2rad(a.getLat()))
                        *
                        Math.sin(deg2rad(b.getLat()))
                        +
                        Math.cos(deg2rad(a.getLat()))
                                *
                                Math.cos(deg2rad(b.getLat()))
                                *
                                Math.cos(deg2rad(longDif));
        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515; // Convert to meters
        distance = distance * 0.8684; // Convert to miles.
        return distance;
    }
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
}
