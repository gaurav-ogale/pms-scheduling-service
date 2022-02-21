package com.citius.models;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DateWiseSchedule implements Serializable {

	private ArrayList<TimeSlots> timeSlots;
	private ArrayList<TimeSlots> breakSlots;
	private LocalDate date;
	private DayOfWeek dayOfWeek;
	private Boolean onLeave = false;
	private TimeSlots dayPlan;

	public DateWiseSchedule() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<TimeSlots> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(TimeSlots timeSlots) {
		LocalTime startTime = timeSlots.getStartTime();
		LocalTime endTime = timeSlots.getEndTime();

		int gapInMinutes = 30;
		int loops = ((int) Duration.between(startTime, endTime).toMinutes() / gapInMinutes);

		ArrayList<TimeSlots> timeSlots1 = new ArrayList<>();
		LocalTime time = startTime;
		for (int i = 1; i <= loops; i++) {
			TimeSlots slot = new TimeSlots();
			slot.setStartTime(time);
			time = time.plusMinutes(gapInMinutes);
			slot.setEndTime(time);

			timeSlots1.add(slot);
		}
		this.timeSlots = timeSlots1;
	}

	public ArrayList<TimeSlots> getBreakSlots() {
		return breakSlots;
	}

	public void setBreakSlots(ArrayList<TimeSlots> breakSlots) {
		this.breakSlots = breakSlots;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Boolean getOnLeave() {
		return onLeave;
	}

	public void setOnLeave(Boolean onLeave) {
		this.onLeave = onLeave;
	}

	public TimeSlots getDayPlan() {
		return dayPlan;
	}

	public void setDayPlan(TimeSlots dayPlan) {
		this.dayPlan = dayPlan;
	}

}
