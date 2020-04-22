package com.bridgelabz.noteservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.noteservice.constants.Constants;
import com.bridgelabz.noteservice.dto.LabelDTO;
import com.bridgelabz.noteservice.dto.Response;
import com.bridgelabz.noteservice.entity.Label;
import com.bridgelabz.noteservice.entity.Note;
import com.bridgelabz.noteservice.exception.LabelException;
import com.bridgelabz.noteservice.exception.NoteException;
import com.bridgelabz.noteservice.repository.LabelRepository;
import com.bridgelabz.noteservice.repository.NoteRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@PropertySource("classpath:message.properties")
public class LabelServiceProvider implements LabelService{
	
	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	/**
	 * Create a new label
	 */
	public Response createLabel(String token, LabelDTO labelDTO) {
		

		log.info("Create Label Method");
		log.info(token);
		ResponseEntity<Response> res = restTemplate.getForEntity(Constants.USER_EXIST_URL + "?token={token}",
				Response.class, token);
		if (res.getStatusCode().value() == 200) {
			Long uId = Long.parseLong(res.getBody().getObj().toString());
			Label label = new Label(labelDTO);
			label.setUserId(uId);
			boolean exist = labelRepository.findAllByUserId(uId).stream()
					.noneMatch(lbl -> lbl.getLabelName().equalsIgnoreCase(labelDTO.getLabelName()));
			if(exist) {
				labelRepository.save(label);
				return new Response(HttpStatus.OK.value(), env.getProperty("212"),labelDTO);
			}
			return new Response(HttpStatus.ALREADY_REPORTED.value(),env.getProperty("108") ,labelDTO);
		}
		else {
			throw new NoteException(404, env.getProperty("104"));
		}
	
	}
	
	/**
	 * Update the existing label based on it's id
	 */
	@Transactional
	public void updateLabel(String token,  LabelDTO labelDTO, Long lId) {
		
		log.info("Update Label Method");
		log.info(token);
		ResponseEntity<Response> res = restTemplate.getForEntity(Constants.USER_EXIST_URL + "?token={token}",
				Response.class, token);
		if (res.getStatusCode().value() == 200) {
			Long uId = Long.parseLong(res.getBody().getObj().toString());
			List<Label> labels = labelRepository.findAllByUserId(uId);
			Label filteredLabel = labels.stream().filter(lbl -> lbl.getLabelId().equals(lId)).findFirst().orElseThrow(() -> new LabelException(404,env.getProperty("109")));
			filteredLabel.setLabelName(labelDTO.getLabelName());
			labelRepository.save(filteredLabel);
		}
		else {
			throw new NoteException(404, env.getProperty("104"));
		}
	}
	
	/**
	 * Delete the label based on it's id
	 */
	@Transactional
	public void deleteLabel(String token, Long lId) {
		
		log.info("Delete Label Method");
		log.info(token);
		ResponseEntity<Response> res = restTemplate.getForEntity(Constants.USER_EXIST_URL + "?token={token}",
				Response.class, token);
		if (res.getStatusCode().value() == 200) {
			Long uId = Long.parseLong(res.getBody().getObj().toString());
			List<Label> labels = labelRepository.findAllByUserId(uId);
			Label filteredLabel = labels.stream().filter(lbl -> lbl.getLabelId().equals(lId)).findFirst().orElseThrow(() -> new LabelException(404,env.getProperty("109")));
			labelRepository.delete(filteredLabel);
		}
		else {
			throw new NoteException(404, env.getProperty("104"));
		}
	}
	
	/**
	 * Get all labels of the user
	 */
	public List<Label> getAllLables(String token){
		
		log.info("Get all Label Method");
		log.info(token);
		ResponseEntity<Response> res = restTemplate.getForEntity(Constants.USER_EXIST_URL + "?token={token}",
				Response.class, token);
		if (res.getStatusCode().value() == 200) {
			Long uId = Long.parseLong(res.getBody().getObj().toString());
			List<Label> labels = labelRepository.findAllByUserId(uId);
			return labels;
		}
		else {
			throw new NoteException(404, env.getProperty("104"));
		}
	}
	
	/**
	 * Get all notes labeled of perticular id 
	 */
	public List<Note> getNoteByLabel(String token, Long lId){
		log.info("Get all Labeled Notes Method");
		log.info(token);
		ResponseEntity<Response> res = restTemplate.getForEntity(Constants.USER_EXIST_URL + "?token={token}",
				Response.class, token);
		if (res.getStatusCode().value() == 200) {
			Long uId = Long.parseLong(res.getBody().getObj().toString());
			List<Label> labels = labelRepository.findAllByUserId(uId);
			Label filteredLabel = labels.stream().filter(lbl -> lbl.getLabelId().equals(lId)).findFirst().orElseThrow(() -> new LabelException(404,env.getProperty("109")));
			return filteredLabel.getNotes();
		}
		else {
			throw new NoteException(404, env.getProperty("104"));
		}
	}
	
}
