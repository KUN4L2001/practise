package com.myapp.practise.service;

import com.myapp.practise.dao.RoleRepository;
import com.myapp.practise.dao.UUserRepository;
import com.myapp.practise.dto.request.RegisterUserRequestDTO;
import com.myapp.practise.dto.response.LoginResponseDTO;
import com.myapp.practise.entity.Role;
import com.myapp.practise.entity.UUser;
import com.myapp.practise.util.JWTUtil;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UMService {
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UUserRepository uUserRepository;
  private final RoleRepository roleRepository;
  private final AuthenticationService authenticationService;
  private final JWTUtil jwtUtil;

  public ResponseEntity<String> register(RegisterUserRequestDTO request) {

    Set<Role> roles = new HashSet<>();

    for (String roleName : request.getRoles()) {
      Role role =
          roleRepository
              .findByName(roleName)
              .orElseGet(() -> roleRepository.save(Role.builder().name(roleName).build()));
      roles.add(role);
    }

    UUser user =
        UUser.builder()
            .username(request.getUsername())
            .password(bCryptPasswordEncoder.encode(request.getPassword()))
            .roles(roles)
            .enabled(true)
            .accountNonExpired(true)
            .accountNonLocked(true)
            .credentialsNonExpired(true)
            .build();

    uUserRepository.save(user);
    return ResponseEntity.ok("User saved successfully");
  }

  public ResponseEntity<LoginResponseDTO> login(String username, String password) {
    UserDetails userDetails = authenticationService.authenticate(username, password);
    String jwtToken = jwtUtil.generateToken(userDetails);
    LoginResponseDTO responseDTO = LoginResponseDTO.builder().jwtToken(jwtToken).build();

    return ResponseEntity.ok(responseDTO);
  }
}
