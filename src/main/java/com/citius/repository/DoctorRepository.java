package com.citius.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citius.model.Doctor;
import com.citius.userentities.User;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

//	@Query(value = "select u.user_id, u.user_first_name,u.user_title,d.specialization,"
//			+ "s.shift_date, s.shift_start_time, s.shift_end_time" + " from users u JOIN doctor d"
//			+ " ON u.user_id = d.user_id"
//			+ " JOIN doctor_shifts s ON d.id=s.doctor_id where d.id= :id", nativeQuery = true)
//	List<DoctorShiftDetails> getDoctorShifts(long id);
	
	@Query(value = "select u.*,d.*,"
			+ "s.*" + " from users u JOIN doctor d"
			+ " ON u.user_id = d.user_id"
			+ " JOIN doctor_shifts s ON d.doctor_id=s.doctor_id where d.doctor_id= :id", nativeQuery = true)
	List<Doctor> getDoctorShifts(long id);
	
	Doctor findByUser(User user);
	
	@Query("from Doctor d where d.user.userId=:userId")
	Doctor getDctorIDFromUserId(Long userId);
	
	@Query("select DISTINCT(d.specialization) from Doctor d")
	List<String> getDoctorSpecializations();
	
	@Query("from Doctor d where d.specialization=:specialization")
	List<Doctor> getAllDoctorsforSpcialization(String specialization);
}
