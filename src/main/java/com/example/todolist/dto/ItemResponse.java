package com.example.todolist.dto;

import java.time.Instant;

public record ItemResponse(Long id, Long checklistId, String content, boolean done, Instant createdAt, Instant updatedAt) {}
