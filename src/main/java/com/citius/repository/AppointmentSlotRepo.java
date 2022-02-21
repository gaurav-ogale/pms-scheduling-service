package com.citius.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citius.model.AppointmentSlots;

public interface AppointmentSlotRepo extends JpaRepository<AppointmentSlots, Long> {

}
