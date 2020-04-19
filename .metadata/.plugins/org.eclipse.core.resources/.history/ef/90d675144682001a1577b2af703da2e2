package com.bridgelabz.fundookeep.service;

import java.util.List;

import com.bridgelabz.fundookeep.dao.Label;
import com.bridgelabz.fundookeep.dao.Note;
import com.bridgelabz.fundookeep.dto.LabelDTO;
import com.bridgelabz.fundookeep.dto.Response;

public interface LabelService {

	public Response createLabel(String token, LabelDTO labelDTO);
	public void updateLabel(String token,  LabelDTO labelDTO, Long lId);
	public void deleteLabel(String token, Long lId);
	public List<Label> getAllLables(String token);
	public List<Note> getNoteByLabel(String token, Long lid);
	
}
