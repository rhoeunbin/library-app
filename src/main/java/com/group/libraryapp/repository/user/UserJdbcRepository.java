package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// sql을 사용해 실제 db와의 통신 담당
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 유저 생성
    public void saveUser(String name, Integer age){
        String sql = "INSERT INTO user(name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, age);
    }

    // 유저 조회
    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

    // 현재 유저가 있는지, 없는지 등을 확인하고 예외 처리 담당 => id로
    public boolean isUserNotExist(long id){ // 1. 함수 만들기 -> 3, jdbcTemplate 연결 + id
        String readSql = "SELECT * FROM user WHERE id = ?"; // 2. Service에서 db연결 코드 복붙해오기
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    // 유저 이름 업데이트
    public void updateUserName(String name, long id){
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    // 현재 유저가 있는지, 없는지 등을 확인하고 예외 처리 담당 => name으로
    public boolean isUserNotExist(String name) {
        String readSql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }

    // 유저 삭제
    public void deleteUser(String name){
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }
}
