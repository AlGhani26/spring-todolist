package com.example.todolist.dto;

import jakarta.validation.constraints.NotBlank;

public record ItemCreateRequest(@NotBlank String content) {}
