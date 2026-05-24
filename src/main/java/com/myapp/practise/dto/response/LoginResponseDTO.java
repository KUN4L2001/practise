package com.myapp.practise.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
  private String jwtToken;
}
