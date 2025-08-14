package com.example.todolist.Controller;

import com.example.todolist.dto.*;
import com.example.todolist.Entity.Item;
import com.example.todolist.Entity.User;
import com.example.todolist.Service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) { this.service = service; }

    // List items in a checklist
    @GetMapping("/checklists/{checklistId}/items")
    public List<ItemResponse> list(@AuthenticationPrincipal User user, @PathVariable Long checklistId){
        return service.list(user, checklistId);
    }

    // Create item in a checklist
    @PostMapping("/checklists/{checklistId}/items")
    public ResponseEntity<ItemResponse> create(@AuthenticationPrincipal User user,
                                               @PathVariable Long checklistId,
                                               @Valid @RequestBody ItemCreateRequest req){
        Item createdItem = service.create(user, checklistId, req);
        return ResponseEntity.ok(new ItemResponse(
            createdItem.getId(),
            createdItem.getChecklist().getId(),
            createdItem.getContent(),
            createdItem.isDone(),
            createdItem.getCreatedAt(),
            createdItem.getUpdatedAt()
        ));
    }

    // Item detail
    @GetMapping("/items/{itemId}")
    public ItemResponse detail(@AuthenticationPrincipal User user, @PathVariable Long itemId){
        return service.get(user, itemId);
    }

    // Update item content
    @PutMapping("/items/{itemId}")
    public ItemResponse update(@AuthenticationPrincipal User user, @PathVariable Long itemId,
                               @Valid @RequestBody ItemUpdateRequest req){
        return service.updateContent(user, itemId, req);
    }

    // Update item status (done/undone)
    @PatchMapping("/items/{itemId}/status")
    public ItemResponse updateStatus(@AuthenticationPrincipal User user, @PathVariable Long itemId,
                                     @RequestBody ItemStatusUpdateRequest req){
        return service.updateStatus(user, itemId, req.done());
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal User user, @PathVariable Long itemId){
        service.delete(user, itemId);
        return ResponseEntity.noContent().build();
    }
}
