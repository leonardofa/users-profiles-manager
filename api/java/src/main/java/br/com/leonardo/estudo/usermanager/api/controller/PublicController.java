package br.com.leonardo.estudo.usermanager.api.controller;

import br.com.leonardo.estudo.usermanager.api.model.AuthRequest;
import br.com.leonardo.estudo.usermanager.api.model.user.UserCreationRequest;
import br.com.leonardo.estudo.usermanager.api.model.user.UserResponse;
import br.com.leonardo.estudo.usermanager.api.service.user.UserCreationService;
import br.com.leonardo.estudo.usermanager.infrastructure.security.CustomUser;
import br.com.leonardo.estudo.usermanager.infrastructure.security.SecurityUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Public")
@RestController
@RequestMapping("public")
@RequiredArgsConstructor
public class PublicController {

  private final UserCreationService creation;

  @PostMapping("users")
  public ResponseEntity<UserResponse> create(@Valid @RequestBody UserCreationRequest body) {
    return ResponseEntity.ok(creation.execute(body));
  }

}
