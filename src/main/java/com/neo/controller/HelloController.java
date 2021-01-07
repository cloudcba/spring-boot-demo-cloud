package com.neo.controller;

import com.neo.annotation.SystemControllerLog;
import com.neo.config.LogFilter;
import com.neo.service.BrandService;
import com.neo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class HelloController {

    private int n = 0;

    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UsersService usersService;

    @GetMapping("/hello")
    public Object sayHello(String name) {
        System.out.println("name: " + name);
        return "hello " + name;
    }


    @RequestMapping("/hello2")
    @LogFilter("保存请求日志")
    public String index() {
        return "Hello Spring Boot 2.0!";
    }


    @RequestMapping("/getUsers")
    @LogFilter("保存请求日志")
    public Object getUsers() {
        return usersService.queryById(1L);
    }


    @RequestMapping("/s")
    @SystemControllerLog(description = "获取用户信息")
    public List<Map<String, Object>> index2() {
        String sql = "select * from users";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(maps);
        return maps;
    }

    /**
     * 分页
     * @return
     */
    @RequestMapping("/getCountry")
    @SystemControllerLog(description = "获取用户信息")
    public List<Map<String, Object>> getCountry() {

        int pagenum = 1;
        int pagerow = 10;

        int starter = (pagenum - 1) * pagerow;

        String sql = "select * from country limit " + starter + " , " + pagerow;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(maps);
        return maps;
    }


    /**
     * 分页
     * @param pagenum
     * @param pagerow
     * @return
     */
    @RequestMapping("/getCountry2")
    @SystemControllerLog(description = "获取用户信息")
    public List<Map<String, Object>> getCountry2(int pagenum, int pagerow) {
        //http://localhost:8092/getCountry?pagenum=1&pagerow=10
        int starter = (pagenum - 1) * pagerow;
        String sql = "select * from country limit " + starter + " , " + pagerow;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(maps);
        return maps;
    }



}