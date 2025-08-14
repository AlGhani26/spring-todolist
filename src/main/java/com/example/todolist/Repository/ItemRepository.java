package com.example.todolist.Repository;

import com.example.todolist.Entity.Checklist;
import com.example.todolist.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByChecklist(Checklist checklist);
    Optional<Item> findByIdAndChecklist(Long id, Checklist checklist);
}
