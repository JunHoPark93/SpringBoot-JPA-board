package com.jaytech.springbootjpa.controllers;

import com.jaytech.springbootjpa.domain.MyData;
import com.jaytech.springbootjpa.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    private MyDataRepository myDataRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<MyData> list = myDataRepository.findAll();
        model.addAttribute("data",list);
        return "index";
    }

}
