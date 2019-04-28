package com.example.demo.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationInput {

    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lng")
    private Double lng;

    @JsonProperty("locationName")
    private String locationName;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
