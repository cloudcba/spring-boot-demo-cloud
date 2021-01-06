package com.neo.service.impl;

import com.neo.dao.CityDao;
import com.neo.entity.City;
import com.neo.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 市级信息(City)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 22:03:43
 */
@Service("cityService")
public class CityServiceImpl implements CityService {
    @Resource
    private CityDao cityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public City queryById(Long id) {
        return this.cityDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<City> queryAllByLimit(int offset, int limit) {
        return this.cityDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param city 实例对象
     * @return 实例对象
     */
    @Override
    public City insert(City city) {
        this.cityDao.insert(city);
        return city;
    }

    /**
     * 修改数据
     *
     * @param city 实例对象
     * @return 实例对象
     */
    @Override
    public City update(City city) {
        this.cityDao.update(city);
        return this.queryById(city.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cityDao.deleteById(id) > 0;
    }
}