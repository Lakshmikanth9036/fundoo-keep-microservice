package com.bridgelabz.fundookeep.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundookeep.dao.Label;
import com.bridgelabz.fundookeep.dao.Note;
import com.bridgelabz.fundookeep.dao.User;
import com.bridgelabz.fundookeep.dto.LabelDTO;
import com.bridgelabz.fundookeep.dto.Response;
import com.bridgelabz.fundookeep.exception.LabelException;
import com.bridgelabz.fundookeep.exception.UserException;
import com.bridgelabz.fundookeep.repository.LabelRepository;
import com.bridgelabz.fundookeep.repository.UserRepository;
import com.bridgelabz.fundookeep.utils.JwtUtils;

@Service
@PropertySource("classpath:message.properties")
public class LabelServiceProvider implements LabelService{

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JwtUtils jwt;
	
	
	/**
	 * Create a new label
	 */
	public Response createLabel(String token, LabelDTO labelDTO) {
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404,env.getProperty("104")));
		Label label = new Label(labelDTO);
		boolean exist = user.getLabels().stream().noneMatch(lbl -> lbl.getLabelName().equalsIgnoreCase(label.getLabelName()));

		if(exist) {
			user.getLabels().add(label);
			repository.save(user);
			return new Response(HttpStatus.OK.value(), env.getProperty("212"),labelDTO);
		}
		return new Response(HttpStatus.ALREADY_REPORTED.value(),env.getProperty("108") ,labelDTO);
	}
	
	/**
	 * Update the existing label based on it's id
	 */
	@Transactional
	public void updateLabel(String token,  LabelDTO labelDTO, Long lId) {
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404,env.getProperty("104")));
		List<Label> labels = user.getLabels();
		Label filteredLabel = labels.stream().filter(lbl -> lbl.getLabelId().equals(lId)).findFirst().orElseThrow(() -> new LabelException(404,env.getProperty("109")));
		filteredLabel.setLabelName(labelDTO.getLabelName());
		repository.save(user);
	}
	
	/**
	 * Delete the label based on it's id
	 */
	@Transactional
	public void deleteLabel(String token, Long lId) {
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404,env.getProperty("104")));
		List<Label> labels = user.getLabels();
		Label filteredLabel = labels.stream().filter(lbl -> lbl.getLabelId().equals(lId)).findFirst().orElseThrow(() -> new LabelException(404,env.getProperty("109")));
		labels.remove(filteredLabel);
		labelRepository.deleteById(filteredLabel.getLabelId());
		repository.save(user);
	}
	
	/**
	 * Get all labels of the user
	 */
	public List<Label> getAllLables(String token){
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404,env.getProperty("104")));
		List<Label> labels = user.getLabels();
		return labels;
	}
	
	/**
	 * Get all notes labeled of perticular id 
	 */
	public List<Note> getNoteByLabel(String token, Long lid){
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404,env.getProperty("104")));
		Label lbl = user.getLabels().stream().filter(label -> label.getLabelId().equals(lid)).findFirst().orElseThrow(() -> new LabelException(404,env.getProperty("109")));
		return lbl.getNotes();
	}
	
}
