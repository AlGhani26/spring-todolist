package com.example.todolist.Entity;

import com.example.todolist.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Checklist {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // e.g., "Grocery List"

    private String color; // for UI (like Google Keep card colors)

    @ManyToOne(optional = false)
    private User owner;

    private Instant createdAt;

    @OneToMany(mappedBy = "checklist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<com.example.todolist.Entity.Item> items = new ArrayList<>();

    @PrePersist
    void onCreate(){ this.createdAt = Instant.now(); }
}
