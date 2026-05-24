package com.myapp.practise.controller;

import com.myapp.practise.dto.request.LoginRequestDTO;
import com.myapp.practise.dto.request.RegisterUserRequestDTO;
import com.myapp.practise.dto.response.LoginResponseDTO;
import com.myapp.practise.service.UMService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UMController {
  private final UMService umService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterUserRequestDTO request) {
    return umService.register(request);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
    return umService.login(request.getUsername(), request.getPassword());
  }

  @PostMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> getAdmin() {
    return ResponseEntity.ok("Admin accessed");
  }

  @PostMapping("/user")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<String> getUser() {
    return ResponseEntity.ok("User accessed");
  }

  @PostMapping("/member")
  @PreAuthorize("hasRole('MEMBER')")
  public ResponseEntity<String> getMember() {
    return ResponseEntity.ok("Member accessed");
  }
}
