package com.neo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
public class SysLog implements Serializable {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String url;

    private Integer time;

    private String method;

    private String params;

    private String ip;

    /**
     * ip地址来源
     */
    private String location;

    @CreationTimestamp
    private Timestamp createTime;
}
