package com.example.demo.service.validator;

import com.example.demo.error.CustomException;
import com.example.demo.model.input.LocationInput;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.entity.Location;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Validator class to validate the json input
 */
public class LocationValidator {

    private LocationInput locationInput;
    public LocationValidator(LocationInput locationInput){
        this.locationInput = locationInput;
    }
    public void validate(LocationRepository locationRepository){
        if(StringUtils.isEmpty(locationInput.getLocationName())){
            throw new CustomException("Location name is empty",null);
        }
        Optional<Location> location = locationRepository.findByLocationName(locationInput.getLocationName());
        if(location.isPresent()){
            throw new CustomException("Location name already exists",null);
        }

    }
}
