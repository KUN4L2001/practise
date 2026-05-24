package com.myapp.practise.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("jwt.token")
public class JWTConfigProps {
  private Long validity;
  private String secretKey;
}
