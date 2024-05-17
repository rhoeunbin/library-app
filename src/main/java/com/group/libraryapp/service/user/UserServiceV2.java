package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 유저 생성
    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    // 유저 조회
    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    // 유저 업데이트
    public void updateUser(UserUpdateRequest request){
        // 원하는 sql : select * from where id = ?;
        // 반환값 : Optional<User>
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new); // 유저가 없다면 예외, 유저가 있다면 결과값 들어오고
        user.updateName(request.getName()); // 들어온 이름
        userRepository.save(user); // 자동으로 user의 이름이 바뀌어있는걸 확인하고 바뀐걸 기준으로 업데이트 쿼리가 날아감
    }

    // 유저 삭제
    public void deleteUser(String name){
        User user = userRepository.findByname(name);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.delete(user); // 무조건 유저가 존재함을 의미
    }
}
