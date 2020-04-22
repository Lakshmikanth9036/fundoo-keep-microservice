package com.bridgelabz.noteservice.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.bridgelabz.noteservice.dto.LabelDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Label implements Serializable{

	@Id
	@GenericGenerator(name = "lId", strategy = "increment")
	@GeneratedValue(generator = "lId")
	@Column(name = "LabelId")
	private Long labelId;
	
	@Column(name = "UserId")
	private Long userId;
	
	@Column(name = "LabelName", unique = true)
	private String labelName;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "labels")
	private List<Note> notes;
	

	public Label(LabelDTO labelDTO) {
		this.labelName = labelDTO.getLabelName();
		notes = new ArrayList<>();
	}
}
