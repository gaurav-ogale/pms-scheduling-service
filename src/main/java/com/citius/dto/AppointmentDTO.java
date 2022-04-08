package com.citius.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class AppointmentDTO {
	private String title;
	private String startTime;
	private String endTime;
	private String appointmentDate;
	private String appointmentStatus;
	private long userId;
}
