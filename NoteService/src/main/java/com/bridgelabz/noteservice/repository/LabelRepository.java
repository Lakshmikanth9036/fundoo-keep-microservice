package com.bridgelabz.noteservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.noteservice.entity.Label;

public interface LabelRepository extends JpaRepository<Label, Long>{

	public List<Label> findAllByUserId(Long userId);

	public Label findByLabelName(String labelName);

}
