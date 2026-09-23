package com.example.demo.controller;

import com.example.demo.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

interface NoteRepository extends MongoRepository<Note, String> {}

/**
 * Minimal write-then-read database test used to verify a CodeBox Cloud MongoDB deployment:
 * POST a note, then GET the list back to confirm it was actually persisted.
 */
@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        try {
            Note saved = noteRepository.save(new Note(note.getText()));
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        try {
            return new ResponseEntity<>(noteRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
