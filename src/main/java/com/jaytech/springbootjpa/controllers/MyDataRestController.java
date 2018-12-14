package com.jaytech.springbootjpa.controllers;

import com.jaytech.springbootjpa.domain.MyData;
import com.jaytech.springbootjpa.services.MyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyDataRestController {

    @Autowired
    private MyDataService myDataService;

    @RequestMapping("/rest")
    public List<MyData> restAll() {

        return myDataService.getAll();
    }

    @RequestMapping("/rest/{id}")
    public MyData restBy(@PathVariable long id) {
        return myDataService.get(id);
    }
}
