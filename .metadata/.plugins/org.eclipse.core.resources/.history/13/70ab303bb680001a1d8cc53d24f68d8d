package com.bridgelabz.fundookeep.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundookeep.constants.Constants;
import com.bridgelabz.fundookeep.dao.Label;
import com.bridgelabz.fundookeep.dao.Note;
import com.bridgelabz.fundookeep.dao.User;
import com.bridgelabz.fundookeep.dto.LabelDTO;
import com.bridgelabz.fundookeep.dto.NoteDTO;
import com.bridgelabz.fundookeep.dto.ReminderDTO;
import com.bridgelabz.fundookeep.dto.Response;
import com.bridgelabz.fundookeep.exception.NoteException;
import com.bridgelabz.fundookeep.exception.UserException;
import com.bridgelabz.fundookeep.repository.NoteRepository;
import com.bridgelabz.fundookeep.repository.UserRepository;
import com.bridgelabz.fundookeep.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@PropertySource("classpath:message.properties")
public class NoteServiceProvider implements NoteService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private Environment env;

	@Autowired
	private JwtUtils jwt;

	@Autowired
	private RestHighLevelClient client;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Creates a new note
	 */
	public void createNote(NoteDTO noteDTO, String token) {

		Long uId = jwt.decodeToken(token);
		Note note = new Note(noteDTO);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		user.getNotes().add(note);
		Note nte = noteRepository.save(note);
		repository.save(user);

		Map<String, Object> documentMapper = objectMapper.convertValue(nte, Map.class);
		IndexRequest indexRequest = new IndexRequest(Constants.INDEX, Constants.TYPE, String.valueOf(note.getNoteId()))
				.source(documentMapper);
		try {
			client.index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update the existing note
	 */
	@Transactional
	public void updateNote(NoteDTO noteDTO, String token, Long noteId) {

		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		List<Note> notes = user.getNotes();
		Note filteredNote = notes.stream().filter(note -> note.getNoteId().equals(noteId)).findFirst()
				.orElseThrow(() -> new NoteException(404, env.getProperty("105")));
		filteredNote.setTitle(noteDTO.getTitle());
		filteredNote.setDescription(noteDTO.getDescription());
		filteredNote.setColor(noteDTO.getColor());
		filteredNote.setNoteUpdated(LocalDateTime.now());
		repository.save(user);

		updateNoteInES(filteredNote);
	}

	/**
	 * Permenentaly delete the existing note
	 */
	@Transactional
	public void deleteNote(String token, Long noteId) {
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		List<Note> notes = user.getNotes();
		Note filteredNote = notes.stream().filter(note -> note.getNoteId().equals(noteId)).findFirst()
				.orElseThrow(() -> new NoteException(404, env.getProperty("105")));
		notes.remove(filteredNote);
		noteRepository.delete(filteredNote);
		repository.save(user);
		
//		DeleteRequest deleteRequest = new DeleteRequest(Constants.INDEX, Constants.TYPE,String.valueOf());
//        DeleteResponse response = client.delete(deleteRequest,RequestOptions.DEFAULT);

	}

	/**
	 * Moves the existing note to bin
	 */
	@Transactional
	public void moveNoteToTrash(String token, Long noteId) {
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		List<Note> notes = user.getNotes();
		Note filteredNote = notes.stream().filter(note -> note.getNoteId().equals(noteId)).findFirst()
				.orElseThrow(() -> new NoteException(404, env.getProperty("105")));
		filteredNote.setTrash(!filteredNote.isTrash());
		filteredNote.setPin(false);
		filteredNote.setNoteUpdated(LocalDateTime.now());
		repository.save(user);

		updateNoteInES(filteredNote);
	}

	/**
	 * Moves the existing note to archive
	 */
	@Transactional
	public void moveNoteToArchive(String token, Long noteId) {
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		List<Note> notes = user.getNotes();
		Note filteredNote = notes.stream().filter(note -> note.getNoteId().equals(noteId)).findFirst()
				.orElseThrow(() -> new NoteException(404, env.getProperty("105")));
		filteredNote.setArchived(!filteredNote.isArchived());
		filteredNote.setPin(false);
		filteredNote.setNoteUpdated(LocalDateTime.now());
		repository.save(user);

		updateNoteInES(filteredNote);
	}

	/**
	 * Pin the existing note
	 */
	@Transactional
	public void pinNote(String token, Long noteId) {
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		List<Note> notes = user.getNotes();
		Note filteredNote = notes.stream().filter(note -> note.getNoteId().equals(noteId)).findFirst()
				.orElseThrow(() -> new NoteException(404, env.getProperty("105")));
		filteredNote.setPin(!filteredNote.isPin());
		filteredNote.setArchived(false);
		filteredNote.setNoteUpdated(LocalDateTime.now());
		repository.save(user);

		updateNoteInES(filteredNote);
	}

	/**
	 * To change the color of the note
	 */
	@Transactional
	public void changeColorOfNote(String token, Long noteId, String color) {
		
		log.info("Change color method");
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		List<Note> notes = user.getNotes();
		Note filteredNote = notes.stream().filter(note -> note.getNoteId().equals(noteId)).findFirst()
				.orElseThrow(() -> new NoteException(404, env.getProperty("105")));
		filteredNote.setColor(color);
		filteredNote.setNoteUpdated(LocalDateTime.now());
		repository.save(user);

		updateNoteInES(filteredNote);
	}
	
	/**
	 * Add the remainder to the note
	 * @param token jwt that contains user token
	 * @param noteId note id for which we need to add remainder
	 * @param remainder date and time
	 */
	@Transactional
	public void addRemainder(String token, Long noteId, ReminderDTO remainder) {
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		List<Note> notes = user.getNotes();
		Note filteredNote = notes.stream().filter(note -> note.getNoteId().equals(noteId)).findFirst()
				.orElseThrow(() -> new NoteException(404, env.getProperty("105")));
		filteredNote.setReminder(remainder.getReminder());
		filteredNote.setNoteUpdated(LocalDateTime.now());
		repository.save(user);
		
		updateNoteInES(filteredNote);
	}
	
	/**
	 * Remove the remainder from note
	 * @param token jwt that contains user token
	 * @param noteId note id from which we need to remove remainder
	 */
	@Transactional
	public void removeRemainder(String token, Long noteId) {
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		List<Note> notes = user.getNotes();
		Note filteredNote = notes.stream().filter(note -> note.getNoteId().equals(noteId)).findFirst()
				.orElseThrow(() -> new NoteException(404, env.getProperty("105")));
		filteredNote.setReminder(null);
		filteredNote.setNoteUpdated(LocalDateTime.now());
		repository.save(user);
		
		updateNoteInES(filteredNote);
	}
	
	/**
	 * Get all remainder notes
	 * @param token jwt that contains user token
	 * @return all notes that have remainder
	 */
	public List<Note> getRemainderNotes(String token){
		List<Note> notes = getNoteById(token);
		List<Note> filteredNotes = new LinkedList<>();
		notes.forEach(note -> {
			if (!note.isTrash() && note.getReminder()!=null)
				filteredNotes.add(note);
		});
		return filteredNotes;
	}
	
	/**
	 * Get all notes user have
	 */
	public List<Note> getAllNotes(String token) {
		
		List<Note> notes = getNoteById(token);
		List<Note> filteredNotes = new LinkedList<>();
		notes.forEach(note -> {
			if (!note.isArchived() && !note.isTrash() && !note.isPin())
				filteredNotes.add(note);
		});
//		filteredNotes.forEach(note -> {
//			Map<String, Object> documentMapper = objectMapper.convertValue(note, Map.class);
//			IndexRequest indexRequest = new IndexRequest(Constants.INDEX, Constants.TYPE, String.valueOf(note.getNoteId()))
//					.source(documentMapper);
//			try {
//				client.index(indexRequest, RequestOptions.DEFAULT);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		});
		return filteredNotes;
	}

	/**
	 * To get all the pinned notes
	 */
	public List<Note> getPinnedNotes(String token) {
		List<Note> notes = getNoteById(token);
		List<Note> filteredNotes = new LinkedList<>();
		notes.forEach(note -> {
			if (note.isPin() && !note.isTrash())
				filteredNotes.add(note);
		});
		return filteredNotes;
	}

	/**
	 * To get all of the archive notes
	 */
	public List<Note> getArchivedNotes(String token) {
		List<Note> notes = getNoteById(token);
		List<Note> filteredNotes = new LinkedList<>();
		notes.forEach(note -> {
			if (note.isArchived() && !note.isTrash())
				filteredNotes.add(note);
		});
		return filteredNotes;
	}

	/**
	 * To get all of the trash notes
	 */
	public List<Note> getTrashNotes(String token) {
		
		List<Note> notes = getNoteById(token);
		List<Note> filteredNotes = new LinkedList<>();
		notes.forEach(note -> {
			if (note.isTrash())
				filteredNotes.add(note);
		});
		return filteredNotes;
	}

	/**
	 * Get all notes sorted by title
	 */
	public List<Note> sortByTitle(String token) {

		List<Note> notes = getNoteById(token);

		List<Note> sortedNote = notes.parallelStream().sorted(Comparator.comparing(Note::getTitle))
				.collect(Collectors.toList());

//		Collections.sort(notes, (n1,n2) -> 
//		{
//			return n1.getTitle().compareTo(n2.getTitle());
//		});
		return sortedNote;
	}

	/**
	 * Get all notes sorted by created time
	 */
	public List<Note> sortByDateAndTime(String token) {
		List<Note> notes = getNoteById(token);

		List<Note> sotedNote = notes.parallelStream().sorted(Comparator.comparing(Note::getNoteUpdated))
				.collect(Collectors.toList());
//		Collections.sort(notes, (n1,n2) -> 
//		{
//			return n1.getNoteCreated().compareTo(n2.getNoteCreated());
//		});
		return sotedNote;
	}

	/**
	 * Add or create a label for the note
	 */
	public Response addOrCreateLable(String token, Long noteId, LabelDTO labelDTO) {
		Long uId = jwt.decodeToken(token);
		Label lb = new Label(labelDTO);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		Note filteredNote = user.getNotes().stream().filter(note -> note.getNoteId().equals(noteId)).findFirst()
				.orElseThrow(() -> new NoteException(404, "105"));
		boolean exist = filteredNote.getLabels().stream()
				.noneMatch(lbl -> lbl.getLabelName().equalsIgnoreCase(labelDTO.getLabelName()));
		if (exist) {
			boolean exst = user.getLabels().stream()
					.noneMatch(lbl -> lbl.getLabelName().equalsIgnoreCase(labelDTO.getLabelName()));
			if (exst) {
				user.getLabels().add(lb);
				filteredNote.getLabels().add(lb);
				filteredNote.setNoteUpdated(LocalDateTime.now());
				lb.getNotes().add(filteredNote);
				repository.save(user);
				
				updateNoteInES(filteredNote);
				
				return new Response(HttpStatus.OK.value(), env.getProperty("209"), labelDTO);
			} else {
				Label l = user.getLabels().stream()
						.filter(lbl -> lbl.getLabelName().equalsIgnoreCase(labelDTO.getLabelName())).findFirst()
						.orElseThrow(() -> new NoteException(404, "Label Doesnt exists"));
				filteredNote.getLabels().add(l);
				filteredNote.setNoteUpdated(LocalDateTime.now());
				l.getNotes().add(filteredNote);
				repository.save(user);
				
				updateNoteInES(filteredNote);
				
				return new Response(HttpStatus.OK.value(), env.getProperty("210"), labelDTO);
			}
		}
		return new Response(HttpStatus.ALREADY_REPORTED.value(), env.getProperty("106"), labelDTO);
	}

	/**
	 * Remove the perticular label from the note
	 */
	@Transactional
	public Response removeLabel(String token, Long noteId, Long labelId) {
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		Note filteredNote = user.getNotes().stream().filter(note -> note.getNoteId().equals(noteId)).findFirst()
				.orElseThrow(() -> new NoteException(404, "105"));
		List<Label> labels = filteredNote.getLabels();
		Label label = filteredNote.getLabels().stream().filter(lbl -> lbl.getLabelId().equals(labelId)).findFirst()
				.orElse(null);
		if (label != null) {
			labels.remove(label);
			filteredNote.setNoteUpdated(LocalDateTime.now());
			noteRepository.save(filteredNote);
			repository.save(user);
			updateNoteInES(filteredNote);
			return new Response(HttpStatus.OK.value(), env.getProperty("211"));
		}
		return new Response(HttpStatus.NOT_FOUND.value(), env.getProperty("107"));
	}

	/**
	 * Search by title and description
	 * @param text title or description
	 * @return list of notes the match's the text
	 */
	public List<Note> getNoteByTitleAndDescription(String text) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices(Constants.INDEX);
		searchRequest.types(Constants.TYPE);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		QueryBuilder query = QueryBuilders.boolQuery()
				.should(QueryBuilders.queryStringQuery(text).lenient(true).field("title").field("description"))
				.should(QueryBuilders.queryStringQuery("*" + text + "*").lenient(true).field("title")
						.field("description"));
		searchSourceBuilder.query(query);
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = null;
		try {
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getSearchResult(searchResponse);
	}

	private List<Note> getSearchResult(SearchResponse response) {

		SearchHit[] searchHit = response.getHits().getHits();

		List<Note> notes = new ArrayList<>();

		for (SearchHit hit : searchHit) {
			notes.add(objectMapper.convertValue(hit.getSourceAsMap(), Note.class));
		}
		return notes;
	}
	
	private List<Note> getNoteById(String token){
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		return user.getNotes();
	}
	
	private void updateNoteInES(Note filteredNote) {
		UpdateRequest updateRequest = new UpdateRequest(Constants.INDEX, Constants.TYPE,
				String.valueOf(filteredNote.getNoteId()));
		updateRequest.doc(objectMapper.convertValue(filteredNote, Map.class));
		try {
			client.update(updateRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
