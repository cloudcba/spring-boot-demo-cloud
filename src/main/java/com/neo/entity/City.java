package com.neo.entity;

import java.io.Serializable;

/**
 * 市级信息(City)实体类
 *
 * @author makejava
 * @since 2021-01-05 22:03:39
 */
public class City implements Serializable {
    private static final long serialVersionUID = 323680432903914528L;

    private Long id;

    private String name;

    private String state;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}