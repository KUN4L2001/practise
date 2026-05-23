package com.myapp.practise.dao;

import com.myapp.practise.entity.UUser;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UUserRepository extends JpaRepository<UUser, UUID> {}
