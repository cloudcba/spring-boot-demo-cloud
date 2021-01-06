package com.neo.entity;

import java.io.Serializable;

/**
 * 国家信息(Country)实体类
 *
 * @author makejava
 * @since 2021-01-05 21:46:01
 */
public class Country implements Serializable {
    private static final long serialVersionUID = -21212407874873259L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String countryname;
    /**
     * 代码
     */
    private String countrycode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

}