package com.myapp.practise.service;

import com.myapp.practise.dao.RecordRepository;
import com.myapp.practise.dto.request.RecordDTO;
import com.myapp.practise.entity.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;

    public ResponseEntity<String> saveRecord(RecordDTO recordDTO) {
        Record record = Record.builder()
                .firstName(recordDTO.getFirstName())
                .secondName(recordDTO.getSecondName())
                .product(recordDTO.getProduct())
                .amount(recordDTO.getAmount())
                .createdAt(LocalDateTime.now())
                .build();

        recordRepository.save(record);
        return ResponseEntity.ok("Record saved successfully");
    }
}
