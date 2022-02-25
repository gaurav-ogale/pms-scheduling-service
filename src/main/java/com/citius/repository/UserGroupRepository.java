package com.citius.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citius.userentities.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

	UserGroup getUserGroupByuserRole(String userGroup);
}
