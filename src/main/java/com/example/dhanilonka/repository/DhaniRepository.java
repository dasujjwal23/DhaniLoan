package com.example.dhanilonka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dhanilonka.model.Employee;

@Repository
public interface DhaniRepository extends JpaRepository<Employee, Integer>{
	
}
