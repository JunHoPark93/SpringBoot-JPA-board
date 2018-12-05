package com.jaytech.springbootjpa.controllers;

import com.jaytech.springbootjpa.domain.MyData;
import com.jaytech.springbootjpa.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;


@Controller
public class MainController {
    @Autowired
    private MyDataRepository myDataRepository;

    @PostConstruct
    public void init() {
        MyData d1 = new MyData();
        d1.setName("kim");
        d1.setAge(123);
        d1.setMail("fdfdf@df.com");
        d1.setMemo("This is a memo");
        myDataRepository.saveAndFlush(d1);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute("formModel") MyData myData, Model model) {
        Iterable<MyData> list = myDataRepository.findAll();
        model.addAttribute("datalist",list);

        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public String form(@ModelAttribute("formModel") MyData myData) {
        myDataRepository.saveAndFlush(myData);

        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        MyData myData = myDataRepository.findById((long)id)
                .orElseThrow(() -> new NullPointerException());

        model.addAttribute("formModel", myData);

        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public String update(@ModelAttribute("formModel") MyData myData) {
        myDataRepository.saveAndFlush(myData);

        return "redirect:/";
    }
}
