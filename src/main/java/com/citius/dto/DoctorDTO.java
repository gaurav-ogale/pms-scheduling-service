package com.citius.dto;

import java.util.HashSet;
import java.util.Set;

import com.citius.userentities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorDTO {

	private long userId;
	private String specialization;
	private User user;
	private Set<DoctorShiftDTO> shifts = new HashSet<DoctorShiftDTO>();
}
