package com.myapp.practise.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "UUSER")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UUser {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String username;
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  private boolean enabled;
  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialsNonExpired;
}
