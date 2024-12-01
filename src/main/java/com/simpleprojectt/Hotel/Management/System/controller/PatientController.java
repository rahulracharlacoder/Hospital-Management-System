package com.simpleprojectt.Hotel.Management.System.controller;

import com.simpleprojectt.Hotel.Management.System.model.Patient;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/patient/api")
public class PatientController {

    HashMap<Integer, Patient> patientHashMap = new HashMap<>();

    @PostMapping("/save")
    public String savepatient(@RequestBody Patient patient) {
        patientHashMap.put(patient.getId(), patient);
        return "Patient name saved successfully";
    }

    @GetMapping("/getallpatient")
    public HashMap<Integer, Patient> getallpatientname() {
        return patientHashMap;
    }

    @GetMapping("/getpatient/{id}")
    public Patient getpatient(@PathVariable int id) {
        Patient patient = patientHashMap.get(id);
        return patient;
    }

    @GetMapping("/getbyname")
    public Patient getpatientbyname(@RequestParam String name) {
        for(Patient patient : patientHashMap.values()) {
            if(patient.getName().equals(name)) {
                return patient;
            }
        }
        return null;
    }

    @GetMapping("/getbydisease")
    public List<Patient> getpatientbyspecialization(@RequestParam String disease) {
        List<Patient> list = new ArrayList<>();
        for(Patient patient : patientHashMap.values()) {
            if(patient.getDisease().equals(disease)) {
                list.add(patient);
            }
        }
        return list;
    }

    @GetMapping("/getbyname&disease")
    public List<Patient> getpatientbynameanddisease(@RequestParam String name, @RequestParam String disease) {
        List<Patient> list = new ArrayList<>();
        for(Patient patient : patientHashMap.values()) {
            if(patient.getName().equals(name) && patient.getDisease().equals(disease)) {
                list.add(patient);
            }
        }
        return list;
    }

    @GetMapping("/getbynameordisease")
    public List<Patient> getpatientbynameordisease(@RequestParam(required = false) String name, @RequestParam(required = false) String disease) {
        List<Patient> list = new ArrayList<>();
        for(Patient patient : patientHashMap.values()) {
            if(name != null && patient.getName().equals(name)) {
                list.add(patient);
            } else if(disease != null && patient.getDisease().equals(disease)){
                list.add(patient);
            }
        }
        return list;
    }

    @DeleteMapping("/delete/{id}")
    public String deletepatient(@PathVariable int id) {
        patientHashMap.remove(id);
        return "Patient removed successfully";
    }

    @PutMapping("/update/{id}")
    public String updatepatient(@PathVariable int id, @RequestBody Patient patient) {
        if(patientHashMap.get(id) != null) {
            patientHashMap.put(id, patient);
            return "Patient updated";
        }
        else {
            return "Patient not found";
        }
    }

}
