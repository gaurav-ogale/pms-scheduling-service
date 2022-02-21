package com.citius.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citius.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
