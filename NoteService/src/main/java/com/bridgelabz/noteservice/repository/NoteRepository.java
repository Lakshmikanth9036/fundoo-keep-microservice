package com.bridgelabz.noteservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.noteservice.entity.Note;


public interface NoteRepository extends JpaRepository<Note, Long>{

	List<Note> findByUserId(Long userId);	

}
