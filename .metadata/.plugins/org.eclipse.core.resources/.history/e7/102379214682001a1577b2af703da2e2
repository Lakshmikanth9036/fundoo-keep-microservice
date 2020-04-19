package com.bridgelabz.fundookeep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundookeep.dto.LabelDTO;
import com.bridgelabz.fundookeep.dto.Response;
import com.bridgelabz.fundookeep.service.LabelService;

@RestController
@RequestMapping("/label")
@CrossOrigin
@PropertySource("classpath:message.properties")
public class LabelController {

	@Autowired
	private LabelService lService;
	
	@PostMapping(value = "/create")
	private ResponseEntity<Response> createLabel(@RequestBody LabelDTO labelDTO,@RequestHeader(name = "header") String token){
		return ResponseEntity.ok().body(lService.createLabel(token, labelDTO));
	}
	
	@PutMapping(value = "/update")
	private ResponseEntity<Response> updateLabel(@RequestBody LabelDTO labelDTO,@RequestHeader(name = "header") String token, Long lid){
		lService.updateLabel(token, labelDTO, lid);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), "Successfully updated"));
	}
	
	@DeleteMapping(value = "/delete")
	private ResponseEntity<Response> deleteLabel(@RequestHeader(name = "header") String token,@RequestParam Long lid){
		lService.deleteLabel(token, lid);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), "Successfully deleted"));
	}
	
	@GetMapping(value = "/getAllLabel")
	private ResponseEntity<Response> getAllLabel(@RequestHeader(name = "header") String token){
		return ResponseEntity.ok().body(new Response(HttpStatus.FOUND.value(), "Found all labels", lService.getAllLables(token)));
	}
	
	@GetMapping(value = "/getNotesOfLable")
	private ResponseEntity<Response> getNotesOfLable(@RequestHeader(name = "header") String token,@RequestParam Long lid){
		return ResponseEntity.ok().body(new Response(HttpStatus.FOUND.value(), "Found all labels", lService.getNoteByLabel(token, lid)));
	}
}
