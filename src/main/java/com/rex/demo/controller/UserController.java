package com.rex.demo.controller;

import com.rex.demo.model.request.UserRequest;
import com.rex.demo.model.response.UserResponse;
import com.rex.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 使用者控制類
 *
 * @author rex
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 新增一個使用者
     * @param userRequest 使用者資料vo
     * @return true = 成功 , false = 失敗
     */
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody UserRequest userRequest){
        return userService.add(userRequest);
    }

    /**
     * 修改一個使用者
     * @param userRequest 使用者資料vo
     * @return true = 成功 , false = 失敗
     */
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody UserRequest userRequest){
        return userService.update(userRequest);
    }
    /**
     * 刪除一個使用者
     * @param userRequest 使用者資料vo
     * @return true = 成功 , false = 失敗
     */
    @DeleteMapping("remove")
    public ResponseEntity<?> remove(@RequestBody UserRequest userRequest){
        return userService.remove(userRequest);
    }

    /**
     * 取得一個使用者
     * @param account 使用者Id
     * @return UserResponse 使用者資料vo
     */
    @GetMapping("get")
    public ResponseEntity<UserResponse> getOne(@RequestParam String account){
        return userService.getOne(account);
    }

    /**
     * 取得使用者列表
     * @return List<UserResponse> 使用者資料vo列表
     */
    @GetMapping("list")
    public ResponseEntity<List<UserResponse>> list(){
        return userService.list();
    }
}
