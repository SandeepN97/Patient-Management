package tech.patientmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.patientmanagement.model.Patient;
import tech.patientmanagement.repository.PatientRepository;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public List<Patient>  showAll(){
       return  patientRepository.findAll();
    }

    @GetMapping("/patient/{patientId}")
    public Patient searchPatient (@PathVariable("patientId") int patientId){
       try {
         return patientRepository.findById(patientId).get(); //  no such element found exception
       }catch(Exception e){
           return null;
       }
    }

    @PostMapping("/patient")
    public String addPatient(@RequestBody Patient patient){
        patientRepository.save(patient);
        return "Patient record successfully added. ";
    }

    @PutMapping("/patient")
    public String updatePatent(@RequestBody Patient newPatient){
        try {
            Patient oldPatient =   patientRepository.findById(newPatient.getPatientId()).get(); //  no such element found exception
             oldPatient.setPatientName(newPatient.getPatientName());
             oldPatient.setPatientEmail(newPatient.getPatientEmail());
             oldPatient.setPatientPhoneNumber(newPatient.getPatientPhoneNumber());
             oldPatient.setPatientDisease(newPatient.getPatientDisease());
             patientRepository.save(oldPatient);
             return "Successfully Updated Patient Record";
        }catch(Exception e){
            return "Record is not available with the given ID";

        }
    }

    @GetMapping("/test")
    public String testEndPoint(){
        return "Working.....";
    }

    @DeleteMapping("/patient/{patientId}")
    public String deletePatient(@PathVariable ("patientId") int patientId){
        try {
            Patient patient =   patientRepository.findById(patientId).get(); //  no such element found exception
            patientRepository.delete(patient);
            return"Patient Data Successfully Deleted.";
        }catch(Exception e){
            return "Patient Record Not Found.";
        }

    }

}
