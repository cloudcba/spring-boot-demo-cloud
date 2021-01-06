package com.neo.controller;

import com.neo.entity.Brand;
import com.neo.service.BrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Brand)表控制层
 *
 * @author makejava
 * @since 2020-12-05 20:51:27
 */
@RestController
@RequestMapping("brand")
public class BrandController {
    /**
     * 服务对象
     */
    @Resource
    private BrandService brandService;

//    @RequestMapping("/showAll")
//    public List<Brand> show(){
//        List<Brand> brands = brandService.queryAll();
//        System.out.println(brands);
//        return brands;
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Brand selectOne(Integer id) {
        return this.brandService.queryById(id);
    }

}