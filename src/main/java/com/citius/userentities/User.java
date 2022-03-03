package com.citius.userentities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(unique = true)
	private String username;

	@Size(min = 1, max = 3, message = "Invalid user title")
	private String userTitle;
	private String userFirstName;
	private String userLastName;

	@NotNull(message = "Email Should Not be Null")
	@NotEmpty
	@NotBlank
	private String userEmail;

	private LocalDate userDOB;

	@Size(min = 10, max = 10, message = "Contact No shoule be of 10 digit")
	private String userContactNo;

	@NotNull(message = "password should not be null")
	private String password;

	private Boolean isActive = true;

//

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
//	@JsonIgnore
//	private Set<User_Roles> userRoles = new HashSet<User_Roles>();

//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", username=" + username + ", userTitle=" + userTitle + ", userFirstName="
//				+ userFirstName + ", userLastName=" + userLastName + ", userEmail=" + userEmail + ", userDOB=" + userDOB
//				+ ", userContactNo=" + userContactNo + ", password=" + password + ", isActive=" + isActive + "]";
//	}

}
