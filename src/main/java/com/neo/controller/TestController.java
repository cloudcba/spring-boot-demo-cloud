package com.neo.controller;

import com.neo.entity.SysLog;
import com.neo.repository.SysLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private SysLogRepo sysLogRepo;

    @GetMapping(value = "/test")
    public String test1(String test){

        return test;
    }

    @GetMapping(value = "/list")
    public ModelAndView list(Model model){

        List<SysLog> sysLogs = sysLogRepo.findAll();
        model.addAttribute("sysLogs",sysLogs);

        return new ModelAndView("/index");
    }
}