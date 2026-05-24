package com.myapp.practise.service;

import com.myapp.practise.dao.UUserRepository;
import com.myapp.practise.entity.UUser;
import com.myapp.practise.pojo.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UUserRepository uUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UUser user = uUserRepository.findByUsername(username);
    if (user != null) {
      return new UserDetailsImpl(user);
    } else {
      throw new UsernameNotFoundException("User not found");
    }
  }
}
