package com.example.demo.dao;

import com.example.demo.dto.UserDto;
import jakarta.annotation.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<UserDto> findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = :username";

        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("username", username);

            UserDto result = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    params,
                    new BeanPropertyRowMapper<>(UserDto.class)
            );
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        String sql = "SELECT EXISTS(SELECT 1 FROM user WHERE username = :username)";

        return namedParameterJdbcTemplate.queryForObject(
                sql,
                new MapSqlParameterSource("username", username),
                Boolean.class
        );
    }

    @Override
    public void insertUser(UserDto user) {
        String sql = "INSERT INTO user (username, password) VALUES (:username, :password)";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", user.getUserName())
                .addValue("password", user.getPassword());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        String sql = "UPDATE user SET password = :password WHERE username = :username";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", username)
                .addValue("password", newPassword);

        int affected = namedParameterJdbcTemplate.update(sql, params);
        if (affected == 0) {
            throw new EmptyResultDataAccessException(1);
        }
    }

    @Override
    public void deleteByUsername(String username) {
        String sql = "DELETE FROM user WHERE username = :username";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", username);

        int affected = namedParameterJdbcTemplate.update(sql, params);
        if (affected == 0) {
            throw new EmptyResultDataAccessException(1);
        }
    }
}
