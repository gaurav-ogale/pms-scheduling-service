package com.citius.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public interface DoctorShiftDetails {

	Long getUserId();

	String getFirstName();

	String getUserTitle();

	String getSpecialization();

	LocalDate getShiftDate();

	LocalTime getShiftStartTime();

	LocalTime getShiftEndTime();

}
