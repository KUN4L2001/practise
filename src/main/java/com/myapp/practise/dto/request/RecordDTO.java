package com.myapp.practise.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecordDTO {
    private String firstName;
    private String secondName;
    private BigDecimal amount;
    private String product;
}
