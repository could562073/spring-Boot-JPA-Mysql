package com.rex.demo.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rex.demo.entity.UserEntity;

/**
 * 使用者回應封裝類(VO)
 *
 * @author rex
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String account;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;

    public UserResponse() {
    }

    public static UserResponse valueOf(UserEntity userEntity){
        UserResponse userResponse = new UserResponse();
        userResponse.setAccount(userEntity.getAccount());
        userResponse.setAddress(userEntity.getAddress());
        userResponse.setPassword(userEntity.getPassword());
        return userResponse;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
