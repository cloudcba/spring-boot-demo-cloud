package com.neo.controller;

import com.github.pagehelper.PageInfo;
import com.neo.entity.Country;
import com.neo.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 国家信息(Country)表控制层
 *
 * @author makejava
 * @since 2021-01-05 21:46:01
 */
@RestController
    @RequestMapping("country")
public class CountryController {
    /**
     * 服务对象
     */
    @Resource
    private CountryService countryService;

    @GetMapping("selectAllLimit")
    public List<Country> getAll() {
//        ModelAndView result = new ModelAndView("index");
        List<Country> countryList = countryService.getAllByWeekend();
//        result.addObject("pageInfo", new PageInfo<Country>(countryList));
//        result.addObject("queryParam", country);
//        result.addObject("page", country.getPage());
//        result.addObject("rows", country.getRows());
        return countryList;
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Country selectOne(Integer id) {
        return this.countryService.queryById(id);
    }

//    @GetMapping("selectCountry")
//    public List<Country> selectOne(int pagenum,int pagerow) {
//        return this.countryService.queryAllByLimit(pagenum,pagerow);
//    }

        @GetMapping("selectCountry")
    public List<Country> selectOne() {
        return this.countryService.queryAllByLimit(1,10);
    }



}