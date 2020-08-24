package com.rex.demo.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 使用者實體類 (PO)
 *
 * @author rex
 */
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /** primary key */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** 帳號 */
    @Column(name = "account")
    private String account;

    /** 密碼 */
    @Column(name = "password")
    private String password;

    /** 使用者名稱 */
    @Column(name = "username")
    private String username;

    /** 地址 */
    @Column(name = "address")
    private String address;

    /** 電話 */
    @Column(name = "phonenumber")
    private String phoneNumber;

    public UserEntity() {
    }

    public UserEntity(Builder builder) {
        this.account = builder.account;
        this.password = builder.password;
        this.username = builder.username;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
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

    /**
     * 使用者建置器內部類
     *
     * Builder pattern impl
     * 優點: 提高建構物件時的彈性與可讀性
     * 缺點: 程式碼變得更冗長
     */
    public static final class Builder {

        private String account;
        private String password;
        private String username;
        private String address;
        private String phoneNumber;

        public Builder setAccount(String account) {
            this.account = account;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
