package com.example.project.entity;

import androidx.annotation.Nullable;

import com.example.project.utils.Helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Note {
    private Integer id;
    private String title;
    private String description;

    private String createdAt;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        this.createdAt = Helper.formatDate(LocalDateTime.now());
    }

    public Note() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Note)) return false;
        Note other = (Note) obj;
        return id.equals(other.id) && title.equals(other.title);
    }
}
