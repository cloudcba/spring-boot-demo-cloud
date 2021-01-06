package com.neo.service.impl;

import com.github.pagehelper.PageHelper;
import com.neo.dao.CountryDao;
import com.neo.entity.Country;
import com.neo.service.CountryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 国家信息(Country)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 21:46:01
 */
@Service("countryService")
public class CountryServiceImpl implements CountryService {
    @Resource
    private CountryDao countryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Country queryById(Integer id) {
        return this.countryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Country> queryAllByLimit(int offset, int limit) {
        return this.countryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param country 实例对象
     * @return 实例对象
     */
    @Override
    public Country insert(Country country) {
        this.countryDao.insert(country);
        return country;
    }

    /**
     * 修改数据
     *
     * @param country 实例对象
     * @return 实例对象
     */
    @Override
    public Country update(Country country) {
        this.countryDao.update(country);
        return this.queryById(country.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.countryDao.deleteById(id) > 0;
    }



    @Override
    public List<Country> getAllByWeekend() {

        int pageNum = 1;
        int pageSize = 10;
        PageHelper.startPage(pageNum, pageSize);
//        if (country.getPage() != null && country.getRows() != null) {
//            PageHelper.startPage(country.getPage(), country.getRows());}
////        }
//        Weekend<Country> weekend = Weekend.of(Country.class);
//        WeekendCriteria<Country, Object> criteria = weekend.weekendCriteria();
//        if (country.getCountryname() != null && country.getCountryname().length() > 0) {
//            criteria.andLike(Country::getCountryname, "%" + country.getCountryname() + "%");
//        }
//        if (country.getCountrycode() != null && country.getCountrycode().length() > 0) {
//            criteria.andLike(Country::getCountrycode, "%" + country.getCountrycode() + "%");
//        }
        return countryDao.queryAll2();
    }
}