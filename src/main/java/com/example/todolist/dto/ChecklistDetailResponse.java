package com.example.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.util.List;

public class ChecklistDetailResponse {
    private Long id;
    private String title;
    private String color;
    private OwnerResponse owner;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "UTC")
    private Instant createdAt;

    private List<ChecklistItemResponse> items;

    public ChecklistDetailResponse(Long id, String title, String color, OwnerResponse owner, 
                                 Instant createdAt, List<ChecklistItemResponse> items) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.owner = owner;
        this.createdAt = createdAt;
        this.items = items;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getColor() { return color; }
    public OwnerResponse getOwner() { return owner; }
    public Instant getCreatedAt() { return createdAt; }
    public List<ChecklistItemResponse> getItems() { return items; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setColor(String color) { this.color = color; }
    public void setOwner(OwnerResponse owner) { this.owner = owner; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public void setItems(List<ChecklistItemResponse> items) { this.items = items; }
}
