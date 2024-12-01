package com.simpleprojectt.Hotel.Management.System.controller;

import com.simpleprojectt.Hotel.Management.System.model.Ward;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ward/api")
public class WardController {

    HashMap<Integer, Ward> wardHashMap = new HashMap<>();

    @PostMapping("/save")
    public String save(@RequestBody Ward ward) {
        wardHashMap.put(ward.getId(), ward);
        return "Ward added successfully";
    }

    @GetMapping("/getallward")
    public HashMap<Integer, Ward> getallnames() {
        return wardHashMap;
    }

    @GetMapping("/getbyid/{id}")
    public Ward getbyid(@PathVariable int id) {
        Ward ward = wardHashMap.get(id);
        return ward;
    }

    @GetMapping("/getbyname")
    public Ward getbyname(@RequestParam String name) {
        for(Ward ward : wardHashMap.values()) {
            if(ward.getName().equals(name)) return ward;
        }
        return null;
    }

    @GetMapping("/getbyfloor")
    public List<Ward> getbyfloor(@RequestParam int floor) {
        List<Ward> list = new ArrayList<>();
        for(Ward ward : wardHashMap.values()) {
            if(ward.getFloor() == floor) list.add(ward);
        }
        return list;
    }

    @GetMapping("/getbyname&floor")
    public List<Ward> getbynameandfloor (@RequestParam String name, @RequestParam int floor) {
        List<Ward> list = new ArrayList<>();
        for(Ward ward : wardHashMap.values()) {
            if(ward.getName().equals(name) && ward.getFloor() == floor) list.add(ward);
        }
        return list;
    }

    @GetMapping("/getbynameorfloor")
    public List<Ward> getwardbynameorfloor (@RequestParam(required = false) String name, @RequestParam(required = false) Integer floor) {
        List<Ward> list = new ArrayList<>();
        for(Ward ward : wardHashMap.values()) {
            if(name != null && ward.getName().equals(name)) list.add(ward);
            else if(floor != null && ward.getFloor() == floor) list.add(ward);
        }
        return list;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteward(@PathVariable int id) {
        wardHashMap.remove(id);
        return "Ward deleted successfully";
    }

    @PutMapping("/update/{id}")
    public String updateward(@PathVariable int id, @RequestBody Ward ward) {
        if(wardHashMap.get(id) != null) {
            wardHashMap.put(id, ward);
            return "Ward updated sucessfully";
        }
        else return "Ward not found";
    }

    @PutMapping("/update2")
    public String updateward2(@RequestBody Ward ward) {
        if(wardHashMap.get(ward.getId()) != null) {
            wardHashMap.put(ward.getId(), ward);
            return "Ward updated successfully";
        }
        else return "Ward not found";
    }

}
