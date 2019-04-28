package com.example.demo.controller;


import com.example.demo.error.CustomException;
import com.example.demo.error.ErrorResponse;
import com.example.demo.error.NotFoundException;
import com.example.demo.model.input.LocationInput;
import com.example.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for locations
 */
@RestController
@RequestMapping(path = "/api")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/ping")
    @RequestMapping("/ping")
    public String ping() {
        return "Ping Succesful";
    }

    /**
     * Add a location
     * @param locationInput
     * @return location
     */
    @PostMapping("/locations")
    public ResponseEntity addLocation(
            @RequestBody(required = true) LocationInput locationInput) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(locationService.addLocation(locationInput));
    }

    /**
     * Get all the locations
     * @return locations
     */
    @GetMapping("/locations")
    public ResponseEntity getAllLocations() {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(locationService.getAllLocations());
    }

    /**
     * Get Location
     * @param id
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/locations/{id}")
    public ResponseEntity getLocation(@PathVariable(name = "id", required = true) String id) throws NotFoundException {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(locationService.getLocation(id));
    }

    /**
     * Get location by name
     * @param locationName
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/locations/names")
    public ResponseEntity getLocationByName(@RequestParam(value = "locationName", required = true) final String locationName) throws NotFoundException {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(locationService.getLocationByName(locationName));
    }

    /**
     * Update location
     * @param id
     * @param locationInput
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/locations/{id}")
    public ResponseEntity updateLocation(@PathVariable(name = "id", required = true) String id,
                                         @RequestBody(required = true) LocationInput locationInput) throws NotFoundException {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(locationService.updateLocation(id,locationInput));

    }

    /**
     * Get locations by current location and radius
     * @param currentLocationName
     * @param radius
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/locations/distance")
    public ResponseEntity getLocationByRadiusAndCurrent(@RequestParam(value = "currentLocationName", required = true) final String currentLocationName
            , @RequestParam(value = "radius", required = true) final Double radius) throws NotFoundException {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(locationService.getAllLocationByRadiusAndName(currentLocationName,radius));
    }

    /**
     *  Delete particular location
     * @param id
     * @return
     * @throws NotFoundException
     */
    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable(name = "id", required = true) String id) throws NotFoundException {
        locationService.deleteLocation(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }

    /**
     * Delete all locations
     * @return
     */
    @DeleteMapping("/locations")
    public ResponseEntity<Void> deleteAllLocation() {
        locationService.deleteAllLocation();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    /**
     * Error cases, returns ErrorResponse which Spring automatically converts to JSON
     * @param e
     * @return response
     */
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(CustomException e) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        return error;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(NotFoundException e, Model model) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        return error;
    }


}
