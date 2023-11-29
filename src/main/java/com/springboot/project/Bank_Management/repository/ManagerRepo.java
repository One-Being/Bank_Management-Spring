package com.springboot.project.Bank_Management.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.project.Bank_Management.dto.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer>
{
	
	@Query("select m from Manager m where m.name =?1")
	public Manager loginMananger(String name);

}
