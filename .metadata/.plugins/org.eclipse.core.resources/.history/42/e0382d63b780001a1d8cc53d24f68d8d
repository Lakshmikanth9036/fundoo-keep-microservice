package com.bridgelabz.fundookeep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundookeep.dao.Note;
import com.bridgelabz.fundookeep.dto.LabelDTO;
import com.bridgelabz.fundookeep.dto.NoteDTO;
import com.bridgelabz.fundookeep.dto.ReminderDTO;
import com.bridgelabz.fundookeep.dto.Response;
import com.bridgelabz.fundookeep.service.NoteService;

@RestController
@RequestMapping("/note")
@CrossOrigin
@PropertySource("classpath:message.properties")
public class NoteController {
	
	@Autowired
	private NoteService nService;
	
	@Autowired
	private Environment env;

	@PostMapping("/create")
	private ResponseEntity<Response> createNote(@RequestBody NoteDTO noteDTO,@RequestHeader(name = "header") String token){
		System.out.println(noteDTO.getTitle()+noteDTO.getDescription());
		nService.createNote(noteDTO, token);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("204")));
	}
	
	@PutMapping("/update/{nId}")
	private ResponseEntity<Response> updateNote(@RequestBody NoteDTO noteDTO,@RequestHeader(name = "header") String token,@PathVariable("nId") Long nId){
		nService.updateNote(noteDTO, token, nId);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("205")));
	}
	
	@PutMapping("/pin/{nId}")
	private ResponseEntity<Response> pin(@RequestHeader(name = "header") String token,@PathVariable("nId") Long nId){
		nService.pinNote(token, nId);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("207")));
	}
	
	@PutMapping("/archive/{nId}")
	private ResponseEntity<Response> archive(@RequestHeader(name = "header") String token,@PathVariable("nId") Long nId){
		nService.moveNoteToArchive(token, nId);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("208")));
	}
	
	@PutMapping("/trash/{nId}")
	private ResponseEntity<Response> trash(@RequestHeader(name = "header") String token,@PathVariable("nId") Long nId){
		nService.moveNoteToTrash(token, nId);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("213")));
	}
	
	@PutMapping("/color")
	private ResponseEntity<Response> changeColor(@RequestHeader(name = "header") String token,@RequestParam Long nId, @RequestParam String color){
		nService.changeColorOfNote(token, nId, color);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("214")));
	}
	
	@PutMapping("/add/remainder")
	private ResponseEntity<Response> addRemainder(@RequestHeader(name = "header") String token, @RequestBody ReminderDTO remainder, @RequestParam Long nId){
		nService.addRemainder(token, nId, remainder);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("215")));
	}
	
	@DeleteMapping("/remove/remainder")
	private ResponseEntity<Response> removeRemainder(@RequestHeader(name = "header") String token, @RequestParam Long nId){
		nService.removeRemainder(token, nId);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("216")));
	}
	
	@DeleteMapping("/delete/{nId}")
	private ResponseEntity<Response> deleteNote(@RequestHeader(name = "header") String token,@PathVariable("nId") Long nId){
		nService.deleteNote(token, nId);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("206")));
	}
	
	@GetMapping("/getallNotes")
	private ResponseEntity<Response> getAllNotes(@RequestHeader(name = "header") String token){
		List<Note> notes = nService.getAllNotes(token);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("302"), notes ));
	}
	
	@GetMapping("/getallPinnedNotes")
	private ResponseEntity<Response> getallPinnedNotes(@RequestHeader(name = "header") String token){
		List<Note> notes = nService.getPinnedNotes(token);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("302"), notes ));
	}
	
	@GetMapping("/getArchivedNotes")
	private ResponseEntity<Response> getArchivedNotes(@RequestHeader(name = "header") String token){
		List<Note> notes = nService.getArchivedNotes(token);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("302"), notes ));
	}
	
	@GetMapping("/getTrashNotes")
	private ResponseEntity<Response> getTrashNotes(@RequestHeader(name = "header") String token){
		List<Note> notes = nService.getTrashNotes(token);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("302"), notes ));
	}
	
	@GetMapping("/getRemainderNotes")
	private ResponseEntity<Response> getRemainderNotes(@RequestHeader(name = "header") String token){
		List<Note> notes = nService.getRemainderNotes(token);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("302"), notes ));
	}
	
	@GetMapping("/sortByTitle")
	private ResponseEntity<Response> getAllNotesSortedByName(@RequestHeader(name = "header") String token){
		List<Note> notes = nService.sortByTitle(token);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("302"), notes ));
	}
	
	@GetMapping("/sortByCreatedTime")
	private ResponseEntity<Response> getAllNotesSortedByTime(@RequestHeader(name = "header") String token){
		List<Note> notes = nService.sortByDateAndTime(token);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("302"), notes ));
	}
	
	@GetMapping("/getNoteByTitleAndDescription/{text}")
	private ResponseEntity<Response> getNoteByTitleAndDescription(@PathVariable("text") String text){
		if(nService.getNoteByTitleAndDescription(text).size() > 0)
			return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), env.getProperty("303"), nService.getNoteByTitleAndDescription(text) ));
		else
			return ResponseEntity.ok().body(new Response(HttpStatus.NOT_FOUND.value(), env.getProperty("405")));
	}
	
	@PutMapping("/add/label")
	private ResponseEntity<Response> createLabel(@RequestBody LabelDTO labelDTO, @RequestHeader(name = "header") String token,@RequestParam Long nId){
		return ResponseEntity.ok().body(nService.addOrCreateLable(token, nId, labelDTO));
	}
	
	@DeleteMapping("/remove/label")
	private ResponseEntity<Response> removeLabel( @RequestHeader(name = "header") String token,@RequestParam Long noteId,@RequestParam Long labelId){
		return ResponseEntity.ok().body(nService.removeLabel(token, noteId, labelId));
	}
	
}
