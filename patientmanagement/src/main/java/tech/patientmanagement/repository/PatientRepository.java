package tech.patientmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.patientmanagement.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

}
