package com.neo.service;

import com.neo.entity.City;

import java.util.List;

/**
 * 市级信息(City)表服务接口
 *
 * @author makejava
 * @since 2021-01-05 22:03:43
 */
public interface CityService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    City queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<City> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param city 实例对象
     * @return 实例对象
     */
    City insert(City city);

    /**
     * 修改数据
     *
     * @param city 实例对象
     * @return 实例对象
     */
    City update(City city);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}