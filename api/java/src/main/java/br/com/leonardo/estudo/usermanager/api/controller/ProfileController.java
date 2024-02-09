package br.com.leonardo.estudo.usermanager.api.controller;

import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileCreationRequest;
import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileResponse;
import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileUpdateRequest;
import br.com.leonardo.estudo.usermanager.api.service.profile.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileCreationService creation;
    private final ProfileUpdateService update;
    private final ProfileReadModelService read;
    private final ProfileDeleteService delete;
    private final ProfileAddUserService addUser;

    @PostMapping
    public ResponseEntity<ProfileResponse> create(@Valid @RequestBody ProfileCreationRequest body) {
        return ResponseEntity.ok(creation.execute(body));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfileResponse> update(@Valid @RequestBody ProfileUpdateRequest body, @PathVariable String id) {
        return ResponseEntity.ok(update.execute(id, body));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileResponse> read(@PathVariable String id) {
        return ResponseEntity.ok(read.execute(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        delete.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/add-user/{user}")
    public ResponseEntity<ProfileResponse> addUser(@PathVariable String id, @PathVariable String user) {
        addUser.execute(id, user);
        return ResponseEntity.noContent().build();
    }

}
