package com.rex.demo.service;

import com.rex.demo.model.request.UserRequest;
import com.rex.demo.model.response.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 使用者介面類別
 *
 * @author rex
 */
public interface IUserService {

    /**
     * 新增一個使用者
     *
     * @param userRequest 使用者資料vo
     * @return true = 成功 , false = 失敗
     */
    ResponseEntity<Boolean> add(UserRequest userRequest);

    /**
     * 修改一個使用者
     *
     * @param userRequest 使用者資料vo
     * @return true = 成功 , false = 失敗
     */
    ResponseEntity<Boolean> update(UserRequest userRequest);

    /**
     * 刪除一個使用者
     *
     * @param userRequest 使用者資料vo
     * @return true = 成功 , false = 失敗
     */
    ResponseEntity<Boolean> remove(UserRequest userRequest);

    /**
     * 取得一個使用者
     *
     * @param account 使用者account
     * @return UserResponse 使用者資料vo
     */
    ResponseEntity<UserResponse> getOne(String account);

    /**
     * 取得使用者列表
     *
     * @return List<UserResponse> 使用者資料vo列表
     */
    ResponseEntity<List<UserResponse>> list();
}
