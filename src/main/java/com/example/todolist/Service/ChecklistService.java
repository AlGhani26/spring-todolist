package com.example.todolist.Service;

import com.example.todolist.dto.ChecklistCreateRequest;
import com.example.todolist.dto.ChecklistDetailResponse;
import com.example.todolist.dto.ChecklistResponse;
import com.example.todolist.dto.ChecklistItemResponse;
import com.example.todolist.dto.OwnerResponse;
import com.example.todolist.Repository.ChecklistRepository;
import com.example.todolist.Entity.User;
import com.example.todolist.Entity.Checklist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChecklistService {
    private final ChecklistRepository repo;

    public ChecklistService(ChecklistRepository repo) { this.repo = repo; }

    public Checklist create(User owner, ChecklistCreateRequest req) {
        Checklist c = Checklist.builder().title(req.title()).color(req.color()).owner(owner).build();
        return repo.save(c);
    }

    public List<ChecklistResponse> list(User owner){
        return repo.findAllByOwnerOrderByCreatedAtDesc(owner).stream()
                .map(c -> new ChecklistResponse(c.getId(), c.getTitle(), c.getColor(), c.getItems().size(), c.getCreatedAt()))
                .toList();
    }

    public Checklist getOwned(User owner, Long id){
        return repo.findByIdAndOwnerId(id, owner.getId())
            .orElseThrow(() -> new IllegalArgumentException("Checklist not found"));
    }

    public ChecklistDetailResponse getChecklistDetail(User owner, Long id) {
        Checklist checklist = repo.findByIdAndOwnerId(id, owner.getId())
            .orElseThrow(() -> new IllegalArgumentException("Checklist not found"));

        OwnerResponse ownerResponse = new OwnerResponse(
            checklist.getOwner().getId(),
            checklist.getOwner().getEmail(),
            checklist.getOwner().getName()
        );

        List<ChecklistItemResponse> items = checklist.getItems().stream()
            .map(item -> new ChecklistItemResponse(
                item.getId(),
                item.getContent(),
                item.isDone()
            ))
            .collect(Collectors.toList());

        return new ChecklistDetailResponse(
            checklist.getId(),
            checklist.getTitle(),
            checklist.getColor(),
            ownerResponse,
            checklist.getCreatedAt(),
            items
        );
    }

    public void delete(User owner, Long id){
        Checklist c = getOwned(owner, id);
        repo.delete(c);
    }
}
