package com.example.demo.dao;

import com.example.demo.dto.DomainDto;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DomainDaoImpl implements DomainDao {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<DomainDto> findAllDomains() {
        String sql = "SELECT * FROM employeedomain ORDER BY name";
        return namedParameterJdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(DomainDto.class)
        );
    }
}