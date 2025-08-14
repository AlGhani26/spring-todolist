package com.example.todolist.Controller;

import com.example.todolist.dto.ChecklistCreateRequest;
import com.example.todolist.dto.ChecklistDetailResponse;
import com.example.todolist.dto.ChecklistResponse;
import com.example.todolist.Entity.User;
import com.example.todolist.Entity.Checklist;
import com.example.todolist.Service.ChecklistService;
import com.example.todolist.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checklists")
public class ChecklistController {
    private final ChecklistService service;
    private final UserRepository userRepository;

    public ChecklistController(ChecklistService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername(); // ini return email
        } else {
            email = principal.toString();
        }
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ChecklistCreateRequest req){
        User user = getCurrentUser();
        Checklist c = service.create(user, req);
        return ResponseEntity.ok(new ChecklistResponse(c.getId(), c.getTitle(), c.getColor(), 0, c.getCreatedAt()));
    }

    @GetMapping
    public List<ChecklistResponse> list(){
        User user = getCurrentUser();
        return service.list(user);
    }

    @GetMapping("/{id}")
    public ChecklistDetailResponse detail(@PathVariable Long id){
        User user = getCurrentUser();
        return service.getChecklistDetail(user, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        User user = getCurrentUser();
        service.delete(user, id);
        return ResponseEntity.noContent().build();
    }
}
