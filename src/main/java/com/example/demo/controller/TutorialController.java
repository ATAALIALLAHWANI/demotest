package com.example.demo.controller;

import com.example.demo.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

// Repository interface
interface TutorialRepository extends MongoRepository<Tutorial, String> {}

@RestController
@RequestMapping("/api/tutorials")
@CrossOrigin(origins = "*")
public class TutorialController {

    @Autowired
    private TutorialRepository tutorialRepository;

    // Create a new tutorial
    @PostMapping
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        try {
            Tutorial _tutorial = tutorialRepository.save(new Tutorial(
                    tutorial.getTitle(),
                    tutorial.getDescription(),
                    tutorial.isPublished()
            ));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all tutorials
    @GetMapping
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        try {
            List<Tutorial> tutorials = tutorialRepository.findAll();
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get tutorial by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable String id) {
        Optional<Tutorial> tutorial = tutorialRepository.findById(id);
        return tutorial.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete tutorial by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable String id) {
        try {
            tutorialRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
