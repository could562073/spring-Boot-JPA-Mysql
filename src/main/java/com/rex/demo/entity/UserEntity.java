package com.rex.demo.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 使用者實體類
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

    /** username */
    private String username;

}
