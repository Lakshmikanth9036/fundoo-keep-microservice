package com.bridgelabz.fundookeep.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.bridgelabz.fundookeep.dto.Collaborator;
import com.bridgelabz.fundookeep.dto.NoteDTO;

@Entity
public class Note {

	@Id
	@GenericGenerator(name = "nId", strategy = "increment")
	@GeneratedValue(generator = "nId")
	@Column(name = "NoteId")
	private Long noteId;

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
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	private List<Label> labels;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	private List<Collaborator> collaborators;
	
	
	public Note() {
	}
	
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

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public LocalDateTime getNoteCreated() {
		return noteCreated;
	}

	public void setNoteCreated(LocalDateTime noteCreated) {
		this.noteCreated = noteCreated;
	}

	public LocalDateTime getNoteUpdated() {
		return noteUpdated;
	}

	public void setNoteUpdated(LocalDateTime noteUpdated) {
		this.noteUpdated = noteUpdated;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	public boolean isPin() {
		return isPin;
	}

	public void setPin(boolean isPin) {
		this.isPin = isPin;
	}

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public LocalDateTime getReminder() {
		return reminder;
	}

	public void setReminder(LocalDateTime reminder) {
		this.reminder = reminder;
	}

	public List<Collaborator> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<Collaborator> collaborators) {
		this.collaborators = collaborators;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", title=" + title + ", description=" + description + ", noteCreated="
				+ noteCreated + ", noteUpdated=" + noteUpdated + ", isArchived=" + isArchived + ", isPin=" + isPin
				+ ", isTrash=" + isTrash + ", color=" + color + ", reminder=" + reminder + ", labels=" + labels + "]";
	}

}
