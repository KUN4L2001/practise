package com.myapp.practise.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class RegisterUserRequestDTO {
  private String username;
  private String password;
  private List<String> roles;
}
