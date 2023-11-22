package nl.jordy.techiteasy.controllers;

import nl.jordy.techiteasy.Television;
import nl.jordy.techiteasy.exceptions.NameTooLongException;
import nl.jordy.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class TelevisionController {

    private HashMap<String, Television> televisions = new HashMap<>();


    // quick way to add dummy data to the hashmap
    @PostMapping("/filltelevision")
    public ResponseEntity<String> fillTelevision() {
        televisions.put("1", new Television("Sony", "QLED", 55, 999, "1"));
        televisions.put("2", new Television("Samsung", "OLED", 65, 1299, "2"));
        televisions.put("3", new Television("Samsung", "Plasma", 75, 1999, "3"));
        return ResponseEntity.ok("Television added");
    }

    // quick way to get all the data from the hashmap
    @GetMapping("/televisions")
    public ResponseEntity<HashMap<String, HashMap<String, String>>> getTelevisions() {

        // handles the exception if the television is not found
        if (televisions == null) {
            throw new NullPointerException("No televisions found in the database");
        }

        // create a new hashmap to store the television data and return it
        HashMap<String, HashMap<String, String>> televisionData = new HashMap<>();
        for (Map.Entry<String, Television> television : televisions.entrySet()) {
            // fetches a hashmap with the television data from the television object and puts it in a new hashmap
            televisionData.put(television.getKey(), television.getValue().getTelevisionInfo());
        }
        return ResponseEntity.ok(televisionData);
    }

    // adding a new television to the hashmap
    @PostMapping("/addtelevision")
    public ResponseEntity<String> addTelevision(@RequestParam String brand, String type, int size, int price, String serialNumber) {

        // checks the length of the name
        if (brand.length() > 20) {
            throw new NameTooLongException("Name too long keep it under 20 characters");
        }
        // create a new television object and add it to the hashmap
        televisions.put(serialNumber, new Television(brand, type, size, price, serialNumber));
        return ResponseEntity.ok("Television added");
    }

    // updating a television in the hashmap
    @PutMapping("/updatetelevision/{serialNumber}")
    public ResponseEntity<String> updateTelevision(@PathVariable String serialNumber, @RequestParam String brand, String type, int size, int price) {

        // handles the exception if the television is not found
        if (televisions.get(serialNumber) == null) {
            throw new RecordNotFoundException("Television not found");
        }

        // update the television
        televisions.put(serialNumber, new Television(brand, type, size, price, serialNumber));
        return ResponseEntity.ok("Television updated");
    }

    // deleting a television from the hashmap
    @DeleteMapping("/deletetelevision/{serialNumber}")
    public ResponseEntity<String> deleteTelevision(@PathVariable String serialNumber) {

        // handles the exception if the television is not found
        if (televisions.get(serialNumber) == null) {
            throw new RecordNotFoundException("Television not found");
        }

        // delete the television
        televisions.remove(serialNumber);
        return ResponseEntity.ok("Television deleted");
    }

    // get a television from the hashmap
    @GetMapping("/television/{serialNumber}")
    public ResponseEntity<HashMap<String, String>> getTelevision(@PathVariable String serialNumber) {

        // handles the exception if the television is not found
        if (televisions.get(serialNumber) == null) {
            throw new RecordNotFoundException("Television not found");
        }
        // fetch the television data and return it
        return ResponseEntity.ok(televisions.get(serialNumber).getTelevisionInfo());
    }

}
