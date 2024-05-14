package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceV1 userServiceV1;

    public UserController(UserServiceV1 userServiceV1) {
        this.userServiceV1 = userServiceV1;
    }

    // 유저 생성
    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        userServiceV1.saveUser(request);
    }

    // 유저 조회
    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userServiceV1.getUsers();
    }

    // 유저 업데이트
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userServiceV1.updateUser(request);
    }

    // 유저 삭제
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userServiceV1.deleteUser(name);
    }
}
