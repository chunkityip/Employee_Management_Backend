package com.example.demo.dao;

import com.example.demo.dto.DomainDto;

import java.util.List;

public interface DomainDao {
    List<DomainDto> findAllDomains();
}