package com.citius.userentities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usergroup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserGroup {
	@Id
	private Long userRoleId;
	private String userRole;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userGroup")
	@JsonIgnore
	private Set<User_Roles> userRoles = new HashSet<User_Roles>();

}
