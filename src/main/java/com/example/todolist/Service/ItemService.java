package com.example.todolist.Service;

import com.example.todolist.Entity.Checklist;
import com.example.todolist.Entity.Item;
import com.example.todolist.Service.ChecklistService;
import com.example.todolist.Repository.ItemRepository;
import com.example.todolist.dto.*;
import com.example.todolist.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repo;
    private final ChecklistService checklistService;

    public ItemService(ItemRepository repo, ChecklistService checklistService) {
        this.repo = repo; this.checklistService = checklistService;
    }

    public Item create(User owner, Long checklistId, ItemCreateRequest req){
        Checklist c = checklistService.getOwned(owner, checklistId);
        Item i = Item.builder().checklist(c).content(req.content()).done(false).build();
        return repo.save(i);
    }

    public List<ItemResponse> list(User owner, Long checklistId){
        Checklist c = checklistService.getOwned(owner, checklistId);
        return repo.findAllByChecklist(c).stream()
                .map(this::toDto)
                .toList();
    }

    public ItemResponse get(User owner, Long itemId){
        Item i = findOwnedItem(owner, itemId);
        return toDto(i);
    }

    public ItemResponse updateContent(User owner, Long itemId, ItemUpdateRequest req){
        Item i = findOwnedItem(owner, itemId);
        i.setContent(req.content());
        return toDto(repo.save(i));
    }

    public ItemResponse updateStatus(User owner, Long itemId, boolean done){
        Item i = findOwnedItem(owner, itemId);
        i.setDone(done);
        return toDto(repo.save(i));
    }

    public void delete(User owner, Long itemId){
        Item i = findOwnedItem(owner, itemId);
        repo.delete(i);
    }

    private Item findOwnedItem(User owner, Long itemId){
        // We fetch by joining owner via checklist
        Item i = repo.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Item not found"));
        if (!i.getChecklist().getOwner().getId().equals(owner.getId()))
            throw new IllegalArgumentException("Item not found");
        return i;
    }

    private ItemResponse toDto(Item i){
        return new ItemResponse(i.getId(), i.getChecklist().getId(), i.getContent(), i.isDone(), i.getCreatedAt(), i.getUpdatedAt());
    }
}