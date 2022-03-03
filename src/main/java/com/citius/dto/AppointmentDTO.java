package com.citius.dto;

import com.citius.userentities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class AppointmentDTO {
	private String startTime;
	private String endTime;
	private String appointmentDate;
	private String appointmentStatus;
	private User user;
}
