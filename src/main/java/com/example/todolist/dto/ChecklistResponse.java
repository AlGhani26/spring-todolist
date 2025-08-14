package com.example.todolist.dto;

import java.time.Instant;

public record ChecklistResponse(Long id, String title, String color, int itemCount, Instant createdAt) {}
