package com.example.demo.dao;

import com.example.demo.dto.EmployeeDto;
import jakarta.annotation.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT EXISTS(SELECT 1 FROM employee WHERE email = :email)";
        return namedParameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource("email", email), Boolean.class);
    }

    @Override
    public Optional<EmployeeDto> findByEmail(String email) {
        String sql = "SELECT * FROM employee WHERE email = :email";

        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("email", email);

            EmployeeDto result = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    params,
                    new BeanPropertyRowMapper<>(EmployeeDto.class)
            );

            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<EmployeeDto> findByFirstnameStartingWith(String firstname) {
        String sql = "SELECT * FROM employee WHERE firstname LIKE :firstname";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstname", firstname + "%");

        return namedParameterJdbcTemplate.query(
                sql,
                params,
                new BeanPropertyRowMapper<>(EmployeeDto.class)
        );
    }

    @Override
    public List<EmployeeDto> findByExperience(int experience) {
        String sql = "SELECT * FROM employee WHERE experience = :experience";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("experience", experience);

        return namedParameterJdbcTemplate.query(
                sql,
                params,
                new BeanPropertyRowMapper<>(EmployeeDto.class)
        );
    }




    @Override
    public void insertEmployee(EmployeeDto employee) {
        String sql = "INSERT INTO employee " +
                "(firstname, lastname, password, dob, phone, email, experience, domain) " +
                "VALUES " +
                "(:firstname, :lastname, :password, :dob, :phone, :email, :experience, :domain)";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstname", employee.getFirstname())
                .addValue("lastname", employee.getLastname())
                .addValue("password", employee.getPassword())
                .addValue("dob", employee.getDob())
                .addValue("phone", employee.getPhone())
                .addValue("email", employee.getEmail())
                .addValue("experience", employee.getExperience())
                .addValue("domain", employee.getDomain());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void updateEmployee(EmployeeDto employee) {
        String sql = "UPDATE employee SET " +
                "firstname = :firstname, " +
                "lastname = :lastname, " +
                "password = :password, " +
                "dob = :dob, " +
                "phone = :phone, " +
                "experience = :experience, " +
                "domain = :domain " +
                "WHERE email = :email";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstname", employee.getFirstname())
                .addValue("lastname", employee.getLastname())
                .addValue("password", employee.getPassword())
                .addValue("dob", employee.getDob())
                .addValue("phone", employee.getPhone())
                .addValue("email", employee.getEmail())
                .addValue("experience", employee.getExperience())
                .addValue("domain", employee.getDomain());

        int affected = namedParameterJdbcTemplate.update(sql, params);
        if (affected == 0) {
            throw new EmptyResultDataAccessException(1);
        }
    }

    @Override
    public void deleteByEmail(String email) {
        String sql = "DELETE FROM employee WHERE email = :email";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", email);

        int affected = namedParameterJdbcTemplate.update(sql, params);
        if (affected == 0) {
            throw new EmptyResultDataAccessException(1);
        }
    }
}