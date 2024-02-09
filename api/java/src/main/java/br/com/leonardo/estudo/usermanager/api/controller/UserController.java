package br.com.leonardo.estudo.usermanager.api.controller;

import br.com.leonardo.estudo.usermanager.api.model.user.UserCreationRequest;
import br.com.leonardo.estudo.usermanager.api.model.user.UserResponse;
import br.com.leonardo.estudo.usermanager.api.model.user.UserUpdateRequest;
import br.com.leonardo.estudo.usermanager.api.service.user.UserCreationService;
import br.com.leonardo.estudo.usermanager.api.service.user.UserDeleteService;
import br.com.leonardo.estudo.usermanager.api.service.user.UserReadModelService;
import br.com.leonardo.estudo.usermanager.api.service.user.UserUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserCreationService creation;
    private final UserUpdateService update;
    private final UserReadModelService read;
    private final UserDeleteService delete;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserCreationRequest body) {
        return ResponseEntity.ok(creation.execute(body));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserUpdateRequest body, @PathVariable String id) {
        return ResponseEntity.ok(update.execute(id, body));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> read(@PathVariable String id) {
        return ResponseEntity.ok(read.execute(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        delete.execute(id);
        return ResponseEntity.noContent().build();
    }

}
