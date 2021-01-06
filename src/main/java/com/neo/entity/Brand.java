package com.neo.entity;

import java.io.Serializable;

/**
 * (Brand)实体类
 *
 * @author makejava
 * @since 2020-12-05 20:51:21
 */
public class Brand implements Serializable {
    private static final long serialVersionUID = -14662327521386057L;

    private Integer bid;

    private String bname;

    private String ctime;


    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

}