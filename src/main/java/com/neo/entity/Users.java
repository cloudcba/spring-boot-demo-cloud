package com.neo.entity;

import java.io.Serializable;

/**
 * (Users)实体类
 *
 * @author makejava
 * @since 2020-12-26 21:37:53
 */
public class Users implements Serializable {
    private static final long serialVersionUID = -34340875366759110L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    private String userSex;

    private String nickName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}