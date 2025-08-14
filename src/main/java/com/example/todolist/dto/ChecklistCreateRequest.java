package com.example.todolist.dto;

import jakarta.validation.constraints.NotBlank;

public record ChecklistCreateRequest(@NotBlank String title, String color) {}
