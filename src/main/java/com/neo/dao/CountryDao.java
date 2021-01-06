package com.neo.dao;

import com.neo.entity.City;
import com.neo.entity.Country;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 国家信息(Country)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-05 21:46:01
 */
public interface CountryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Country queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Country> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param country 实例对象
     * @return 对象列表
     */
    List<Country> queryAll(Country country);

    List<Country> queryAll2();

    /**
     * 新增数据
     *
     * @param country 实例对象
     * @return 影响行数
     */
    int insert(Country country);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Country> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Country> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Country> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Country> entities);

    /**
     * 修改数据
     *
     * @param country 实例对象
     * @return 影响行数
     */
    int update(Country country);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}