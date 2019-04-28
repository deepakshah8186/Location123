package com.example.demo.service.mapper;

import com.example.demo.model.response.LocationResponse;
import com.example.demo.repository.entity.Location;
import org.springframework.stereotype.Component;

/**
 * Mapper class to map the entity to response
 */
@Component
public class ResponseMapper {
    public LocationResponse mapResponse(Location location){
        LocationResponse response = new LocationResponse();
        response.setId(location.getId());
        response.setLocationName(location.getLocationName());
        response.setLng(location.getLng());
        response.setLat(location.getLat());
        return response;
    }
}