package com.springboot.project.Bank_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.project.Bank_Management.dto.Branch;

public interface BranchRepo extends JpaRepository<Branch, Integer>
{

}
