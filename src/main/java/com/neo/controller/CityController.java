package com.neo.controller;

import com.neo.annotation.SystemControllerLog;
import com.neo.entity.City;
import com.neo.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 市级信息(City)表控制层
 *
 * @author makejava
 * @since 2021-01-05 22:03:44
 */
@RestController
@RequestMapping("city")
public class CityController {
    /**
     * 服务对象
     */
    @Resource
    private CityService cityService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
//    @SystemControllerLog(description = "获取用户信息")
    public City selectOne(Long id) {
        return this.cityService.queryById(id);
    }

}