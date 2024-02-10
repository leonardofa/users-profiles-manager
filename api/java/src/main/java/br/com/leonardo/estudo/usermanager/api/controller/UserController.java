package br.com.leonardo.estudo.usermanager.api.controller;

import br.com.leonardo.estudo.usermanager.api.model.user.UserResponse;
import br.com.leonardo.estudo.usermanager.api.model.user.UserUpdateRequest;
import br.com.leonardo.estudo.usermanager.api.service.user.UserDeleteService;
import br.com.leonardo.estudo.usermanager.api.service.user.UserReadModelService;
import br.com.leonardo.estudo.usermanager.api.service.user.UserUpdateService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "security_basic_auth")
@Tag(name = "Users")
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

  private final UserUpdateService update;
  private final UserReadModelService read;
  private final UserDeleteService delete;

  @PreAuthorize("@securityUtil.authUser(#id)")
  @PutMapping("{id}")
  public ResponseEntity<UserResponse> update(@Valid @RequestBody UserUpdateRequest body, @PathVariable String id) {
    return ResponseEntity.ok(update.execute(id, body));
  }

  @PreAuthorize("hasAuthority('ADMINISTRATOR') or @securityUtil.authUser(#id)")
  @GetMapping("{id}")
  public ResponseEntity<UserResponse> read(@PathVariable String id) {
    return ResponseEntity.ok(read.execute(id));
  }

  @PreAuthorize("hasAuthority('ADMINISTRATOR') or @securityUtil.authUserIdentification(#identification)")
  @GetMapping("find/identification/{identification}")
  public ResponseEntity<UserResponse> readByIdentification(@PathVariable String identification) {
    return ResponseEntity.ok(read.executeByIdentification(identification));
  }

  @PreAuthorize("hasAuthority('ADMINISTRATOR')")
  @GetMapping
  public ResponseEntity<List<UserResponse>> all() {
    return ResponseEntity.ok(read.list());
  }

  @PreAuthorize("hasAuthority('ADMINISTRATOR')")
  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    delete.execute(id);
    return ResponseEntity.noContent().build();
  }

}
