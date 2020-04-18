package com.bridgelabz.fundookeep.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.bridgelabz.fundookeep.dto.LabelDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Label {

	@Id
	@GenericGenerator(name = "lId", strategy = "increment")
	@GeneratedValue(generator = "lId")
	@Column(name = "LabelId")
	private Long labelId;
	
	@Column(name = "LabelName", unique = true)
	private String labelName;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "labels")
	private List<Note> notes;
	
	public Label() {
	}

	public Label(LabelDTO labelDTO) {
		this.labelName = labelDTO.getLabelName();
		notes = new ArrayList<>();
	}

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	
	
}
