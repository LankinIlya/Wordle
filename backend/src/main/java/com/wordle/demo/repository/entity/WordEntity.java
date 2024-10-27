package com.wordle.demo.repository.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "words")
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    public WordEntity() {
    }

    public WordEntity(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
