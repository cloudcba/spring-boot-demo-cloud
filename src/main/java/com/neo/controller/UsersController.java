package com.neo.controller;

import com.neo.entity.Users;
import com.neo.service.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Users)表控制层
 *
 * @author makejava
 * @since 2020-12-26 21:37:53
 */
@RestController
@RequestMapping("users")
public class UsersController {
    /**
     * 服务对象
     */
    @Resource
    private UsersService usersService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Users selectOne(Long id) {
        return this.usersService.queryById(id);
    }

}