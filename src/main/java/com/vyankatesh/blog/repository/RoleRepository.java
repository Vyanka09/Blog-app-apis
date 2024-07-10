package com.vyankatesh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vyankatesh.blog.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
