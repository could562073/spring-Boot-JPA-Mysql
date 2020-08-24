package com.rex.demo.service.Impl;

import com.rex.demo.entity.UserEntity;
import com.rex.demo.model.request.UserRequest;
import com.rex.demo.model.response.UserResponse;
import com.rex.demo.repository.UserRepository;
import com.rex.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用者實作類別 CRUD Example
 *
 * @author rex
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity<Boolean> add(UserRequest userRequest) {
        if (userRepository.existsByAccount(userRequest.getAccount())) {
            throw new IllegalArgumentException("Account exists");
        }

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
    @Transactional
    public ResponseEntity<Boolean> update(UserRequest userRequest) {
        UserEntity userEntity = userRepository.findByAccount(userRequest.getAccount());
        if(userEntity == null) {
            throw new IllegalArgumentException("Account not found");
        }

        userEntity.setAccount(userRequest.getAccount());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setAddress(userRequest.getAddress());
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());

        userRepository.save(userEntity);

        return ResponseEntity.ok(true);
    }

    @Override
    @Transactional
    public ResponseEntity<Boolean> remove(UserRequest userRequest) {
        if (!userRepository.existsByAccount(userRequest.getAccount())) {
            throw new IllegalArgumentException("Account does not exist");
        }

        UserEntity userEntity = new UserEntity.Builder()
                .setAccount(userRequest.getAccount())
                .build();

        userRepository.delete(userEntity);

        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<UserResponse> getOne(String account) {
        if (!userRepository.existsByAccount(account)) {
            throw new IllegalArgumentException("Account does not exist");
        }

        UserEntity userEntity = userRepository.findByAccount(account);
        UserResponse userResponse = UserResponse.valueOf(userEntity);

        return ResponseEntity.ok(userResponse);
    }

    @Override
    public ResponseEntity<List<UserResponse>> list() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();

        userEntityList.forEach(object -> userResponseList.add(UserResponse.valueOf(object)));

        return ResponseEntity.ok(userResponseList);
    }
}
