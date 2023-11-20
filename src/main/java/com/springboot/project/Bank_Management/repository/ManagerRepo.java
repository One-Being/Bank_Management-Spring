package com.springboot.project.Bank_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.Bank_Management.dto.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer>
{

}
