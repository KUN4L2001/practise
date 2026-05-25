package com.myapp.practise.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "RECORD")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "UUID", nullable = false, updatable = false)
    private UUID uuid;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "SECOND_NAME")
    private String secondName;
    @Column(name = "PRODUCT")
    private String product;
    @Column(name = "AMOUNT", precision = 10, scale = 2)
    private BigDecimal amount;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
}
