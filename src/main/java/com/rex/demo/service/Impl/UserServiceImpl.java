package com.rex.demo.service.Impl;

import com.rex.demo.entity.UserEntity;
import com.rex.demo.model.request.UserRequest;
import com.rex.demo.model.response.UserResponse;
import com.rex.demo.repository.UserRepository;
import com.rex.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 使用者實作類別
 *
 * @author rex
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<Boolean> add(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity.Builder()
                .setAccount(userRequest.getAccount())
                .setPassword(userRequest.getPassword())
                .setUsername(userRequest.getUsername())
                .setAddress(userRequest.getAddress())
                .setPhoneNumber(userRequest.getPhoneNumber())
                .build();
        userRepository.save(userEntity);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<Boolean> update(UserRequest userRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> remove(UserRequest userRequest) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> getOne(int userId) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserResponse>> list() {
        return null;
    }
}
