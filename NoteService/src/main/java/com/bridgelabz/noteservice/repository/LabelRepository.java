package com.bridgelabz.noteservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.noteservice.entity.Label;

public interface LabelRepository extends JpaRepository<Label, Long>{

}
