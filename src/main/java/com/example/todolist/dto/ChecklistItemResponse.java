package com.example.todolist.dto;

public class ChecklistItemResponse {
    private Long id;
    private String content;
    private boolean done;

    public ChecklistItemResponse(Long id, String content, boolean done) {
        this.id = id;
        this.content = content;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
