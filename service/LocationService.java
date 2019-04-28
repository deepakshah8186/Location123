package com.example.demo.service;

import com.example.demo.common.Utility;
import com.example.demo.error.LocationError;
import com.example.demo.error.NotFoundException;
import com.example.demo.model.input.LocationInput;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.entity.Location;
import com.example.demo.service.validator.LocationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService implements LocationServiceI{

    @Autowired
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location addLocation(LocationInput locationInput) {
        // validate by location name
        LocationValidator posValidator = new LocationValidator(locationInput);
        posValidator.validate(locationRepository);

        Location location = getLocationEntity(locationInput);
        return locationRepository.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocation(String id) throws NotFoundException {
        Optional<Location> location = locationRepository.findById(id);
        if (!location.isPresent()) {
            throw new NotFoundException(LocationError.LOCATION_NOT_FOUND + id);
        }
        return location.get();
    }

    @Override
    public Location updateLocation(String id, LocationInput locationInput) throws NotFoundException {
        Optional<Location> location = locationRepository.findById(id);
        if (!location.isPresent()) {
            throw new NotFoundException(LocationError.LOCATION_NOT_FOUND + id);
        }
        Location entity = location.get();
        entity.setLat(locationInput.getLat());
        entity.setLng(locationInput.getLng());
        entity.setLocationName(locationInput.getLocationName());
        return locationRepository.save(location.get());
    }

    @Override
    public Location getLocationByName(String locationName) throws NotFoundException {
        Optional<Location> location = locationRepository.findByLocationName(locationName);
        if (!location.isPresent()) {
            throw new NotFoundException(LocationError.LOCATION_NOT_FOUND + locationName);
        }
        return location.get();
    }

    @Override
    public List<Location> getAllLocationByRadiusAndName(String locationName, Double radius) throws NotFoundException {
        Optional<Location> location = locationRepository.findByLocationName(locationName);
        if (!location.isPresent()) {
            throw new NotFoundException(LocationError.LOCATION_NOT_FOUND + locationName);
        }

        List<Location> allLocations = locationRepository.findAll();

        List<Location> locations = allLocations.stream()
                .filter(loc -> Utility.distanceBetween(location.get(), loc) <= radius)
                .collect(Collectors.toList());
        return locations;
    }

    @Override
    public void deleteLocation(String id) throws NotFoundException {
        Location location = getLocation(id);
        locationRepository.delete(location);
    }

    public void deleteAllLocation() {
        locationRepository.deleteAll();
    }

    private Location getLocationEntity(LocationInput locationInput) {
        Location location = new Location(locationInput.getLng(), locationInput.getLat(), locationInput.getLocationName());
        return location;
    }

}
