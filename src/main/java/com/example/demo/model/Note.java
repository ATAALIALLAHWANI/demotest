package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private String text;
    private String createdAt;

    public Note() {}

    public Note(String text) {
        this.text = text;
        this.createdAt = Instant.now().toString();
    }

    // Getters and setters
    public String getId() { return id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Note [id=" + id + ", text=" + text + ", createdAt=" + createdAt + "]";
    }
}
