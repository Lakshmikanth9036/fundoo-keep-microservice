package com.bridgelabz.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.userservice.entity.Note;


public interface NoteRepository extends JpaRepository<Note, Long>{	

}
