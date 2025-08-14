package com.example.todolist.Repository;

import com.example.todolist.Entity.User;
import com.example.todolist.Entity.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
    List<Checklist> findAllByOwnerOrderByCreatedAtDesc(User owner);
    Optional<Checklist> findByIdAndOwnerId(Long id, Long ownerId);

}
