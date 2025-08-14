package com.example.todolist.dto;

import jakarta.validation.constraints.NotBlank;

public record ItemUpdateRequest(@NotBlank String content) {}
