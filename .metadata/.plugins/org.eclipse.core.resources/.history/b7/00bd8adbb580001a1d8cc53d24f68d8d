package com.bridgelabz.fundookeep.service;

import java.util.List;

import com.bridgelabz.fundookeep.dao.Note;
import com.bridgelabz.fundookeep.dto.LabelDTO;
import com.bridgelabz.fundookeep.dto.NoteDTO;
import com.bridgelabz.fundookeep.dto.ReminderDTO;
import com.bridgelabz.fundookeep.dto.Response;

public interface NoteService {

	public void createNote(NoteDTO noteDTO,String token);
	public void updateNote(NoteDTO noteDTO,String token, Long noteId) ;
	public void deleteNote(String token, Long noteId);
	public List<Note> getAllNotes(String token);
	public List<Note> getPinnedNotes(String token);
	public List<Note> getArchivedNotes(String token);
	public List<Note> getTrashNotes(String token);
	public void moveNoteToTrash(String token, Long noteId);
	public void moveNoteToArchive(String token, Long noteId);
	public void pinNote(String token, Long noteId);
	public void changeColorOfNote(String token,Long noteId,String color) ;
	public List<Note> sortByTitle(String token);
	public List<Note> sortByDateAndTime(String token);
	public Response addOrCreateLable(String token,Long noteId,LabelDTO labelDTO);
	public Response removeLabel(String token,Long noteId,Long labelId);
	public List<Note> getNoteByTitleAndDescription(String text);
	public List<Note> getRemainderNotes(String token);
	public void removeRemainder(String token, Long noteId);
	public void addRemainder(String token, Long noteId, ReminderDTO remainder);
	
}
