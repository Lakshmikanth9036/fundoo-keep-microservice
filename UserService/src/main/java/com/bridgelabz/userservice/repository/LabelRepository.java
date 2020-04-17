package com.bridgelabz.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.userservice.entity.Label;


public interface LabelRepository extends JpaRepository<Label, Long>{

}
