package com.citius.dto;

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
public class DoctorShiftDTO {

	private Long id;
	private String shiftDate;
	private String shiftStartTime;
	private String shiftEndTime;

}
