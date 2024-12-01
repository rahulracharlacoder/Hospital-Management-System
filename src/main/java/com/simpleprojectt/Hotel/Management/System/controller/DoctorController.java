package com.simpleprojectt.Hotel.Management.System.controller;

import com.simpleprojectt.Hotel.Management.System.model.Doctor;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/doctor/api")
public class DoctorController {

    HashMap<Integer, Doctor> doctorHashMap = new HashMap<>();

    @PostMapping("/save")
    public String saveDoctor(@RequestBody Doctor doctorRequest) {
        doctorHashMap.put(doctorRequest.getId(), doctorRequest);
        return "Doctor saved successfully";
    }

    @GetMapping("/findAll")
    public HashMap<Integer, Doctor> getAllDoctors() {
        return doctorHashMap;
    }

    @GetMapping("/find/{doctorid}")
    public Doctor getDoctorById(@PathVariable("doctorid") int DoctorId) {
        Doctor doctor = doctorHashMap.get(DoctorId);
        return doctor;
    }
   @GetMapping("/findByName")
    public Doctor getDoctorByName(@RequestParam("doctorname") String doctorName) {
        for(Doctor doctor: doctorHashMap.values()) {
            if(doctor.getName().equals(doctorName)) return doctor;
        }
        return null;
    }

    @GetMapping("/findspecialization")
    public List<Doctor> getDoctorbyspecialization(@RequestParam("doctorspecialization") String doctorspecialization) {
        List<Doctor> list = new ArrayList<>();
        for(Doctor doctor: doctorHashMap.values()) {
            if(doctor.getSpecialization().equalsIgnoreCase(doctorspecialization)) {
                list.add(doctor);
            }
        }
        return list;
    }

    @GetMapping("/findage&specialization/{age}")
    public List<Doctor> getnameandspecialization(@PathVariable int age, @RequestParam String specialization) {
        List<Doctor> list = new ArrayList<>();
        for(Doctor doctor: doctorHashMap.values()) {
            if(doctor.getAge()==age && doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                list.add(doctor);
            }
        }
        return list;
    }

    @GetMapping("/findageorspecialization")
    public List<Doctor> getnameorspecialization(@RequestParam(required = false) Integer age, @RequestParam(required = false) String specialization) {
        List<Doctor> list = new ArrayList<>();
        for(Doctor doctor: doctorHashMap.values()) {
            if(age != null && doctor.getAge()==age) {
                list.add(doctor);
            } else if(specialization != null && doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                list.add(doctor);
            }
        }
        return list;
    }

    @DeleteMapping("/deletedoctor/{id}")
    public String deletedoctor(@PathVariable int id) {
        doctorHashMap.remove(id);
        return "Doctor removed " +id+ " successfully";
    }

    @PutMapping("/update/{id}")
    public String updatedoctor(@PathVariable int id, @RequestBody Doctor doctor) {
        Doctor doctordetails = doctorHashMap.get(id);
        if(doctordetails != null) {
            doctorHashMap.put(id, doctor);
            return "Doctor updated successfully";
        }
        else {
            return "Doctor not found";
        }
    }
}
