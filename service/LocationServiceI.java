package com.example.demo.service;

import com.example.demo.error.NotFoundException;
import com.example.demo.model.input.LocationInput;
import com.example.demo.repository.entity.Location;

import java.util.List;

public interface LocationServiceI {

    Location addLocation(LocationInput locationInput);
    List<Location> getAllLocations();
    Location getLocation(String id) throws NotFoundException;
    Location updateLocation(String id, LocationInput locationInput) throws NotFoundException;
    Location getLocationByName(String locationName) throws NotFoundException;
    List<Location> getAllLocationByRadiusAndName(String locationName, Double radius) throws NotFoundException;
    void deleteLocation(String id) throws NotFoundException;
}
