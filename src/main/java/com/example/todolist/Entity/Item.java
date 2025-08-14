package com.example.todolist.Entity;

import com.example.todolist.Entity.Checklist;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Checklist checklist;

    @Column(nullable = false)
    private String content; // e.g., "Buy milk"

    private boolean done;

    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    void onCreate(){ this.createdAt = Instant.now(); this.updatedAt = this.createdAt; }
    @PreUpdate
    void onUpdate(){ this.updatedAt = Instant.now(); }
}
