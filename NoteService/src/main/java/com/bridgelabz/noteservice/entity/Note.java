package com.bridgelabz.noteservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.bridgelabz.noteservice.dto.NoteDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@ToString
public class Note implements Serializable{

	@Id
	@GenericGenerator(name = "nId", strategy = "increment")
	@GeneratedValue(generator = "nId")
	@Column(name = "NoteId")
	private Long noteId;
	
	@Column(name = "UserId")
	private Long userId;

	@Column(name = "Title")
	private String title;

	@Column(name = "Description")
	private String description;

	@Column(name = "NoteCreatedTime")
	private LocalDateTime noteCreated;

	@Column(name = "NoteUpdatedTime")
	private LocalDateTime noteUpdated;

	@Column(name = "isArchived", columnDefinition = "bit(1) default 0")
	private boolean isArchived;

	@Column(name = "isPin", columnDefinition = "bit(1) default 0")
	private boolean isPin;

	@Column(name = "isTrash", columnDefinition = "bit(1) default 0")
	private boolean isTrash;

	@Column(name = "Color", columnDefinition = "varchar(10) default 'White'")
	private String color;
	
	@Column
	private LocalDateTime reminder;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	private List<Label> labels;
	
	public Note(NoteDTO noteDTO) {
		this.title = noteDTO.getTitle();
		this.description = noteDTO.getDescription();
		this.noteCreated = LocalDateTime.now();
		this.noteUpdated = LocalDateTime.now();
		this.isArchived = false;
		this.isPin = false;
		this.isTrash = false;
		if(noteDTO.getColor() != null)
			this.color = noteDTO.getColor();
		else
			this.color = "White";
	}

}
