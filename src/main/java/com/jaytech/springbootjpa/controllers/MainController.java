package com.jaytech.springbootjpa.controllers;

import com.jaytech.springbootjpa.domain.MyData;
import com.jaytech.springbootjpa.repositories.MyDataRepository;
import com.jaytech.springbootjpa.services.MyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@Controller
public class MainController {
    @Autowired
    private MyDataRepository myDataRepository;

    @Autowired
    private MyDataService myDataService;

    @PostConstruct
    public void init() {
        MyData d1 = new MyData();
        d1.setName("kim");
        d1.setAge(123);
        d1.setMail("fdfdf@df.com");
        d1.setMemo("000-111-1122");
        myDataRepository.saveAndFlush(d1);

        MyData d2 = new MyData();
        d2.setName("park");
        d2.setAge(51);
        d2.setMail("fdfdf@df.com");
        d2.setMemo("000-111-1122");
        myDataRepository.saveAndFlush(d2);

        MyData d3 = new MyData();
        d3.setName("pa");
        d3.setAge(51);
        d3.setMail("fdfdf@df.com");
        d3.setMemo("000-111-1122");
        myDataRepository.saveAndFlush(d3);

        MyData d4 = new MyData();
        d4.setName("april");
        d4.setAge(34);
        d4.setMail("fdfdf@df.com");
        d4.setMemo("000-111-1122");
        myDataRepository.saveAndFlush(d4);

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute("formModel") MyData myData, Model model) {
        //List<MyData> list = myDataRepository.findAllOrderByName();
        //List<MyData> list = dao.findByAge(50, 100); // for check
        //List<MyData> list = myDataRepository.findByAge(30, 40); // for check
        List<MyData> list = myDataService.getAll();
        model.addAttribute("datalist",list);

        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public String form(@ModelAttribute("formModel") @Validated MyData myData,
                       BindingResult result,
                       Model model) {

        if(!result.hasErrors()) {
            myDataRepository.saveAndFlush(myData);
            return "redirect:/";
        } else {
            Iterable<MyData> list =  myDataRepository.findAll();
            model.addAttribute("datalist", list);
            model.addAttribute("msg", "error~~~");

            return "index";
        }
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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, Model model) {
        MyData myData = myDataRepository.findById((long)id)
                .orElseThrow(() -> new NullPointerException());
        model.addAttribute("formModel", myData);

        return "delete";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public String remove(@ModelAttribute("formModel") MyData myData, @RequestParam long id) {
        myDataRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
    public String findName(@ModelAttribute("formModel") MyData myData, @PathVariable String name, Model model) {
        List<MyData> list = myDataRepository.findByNameLike(name);

        model.addAttribute("datalist",list);

        return "index";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(Model model) {
        model.addAttribute("title", "find page");
        model.addAttribute("msg", "find mydata");
        model.addAttribute("value", "");

        List<MyData> list = myDataService.getAll();
        model.addAttribute("datalist", list);
        return "find";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String search(@RequestParam String name, Model model) {
        if(name == "") {
            return "redirect:/find";
        } else {
            model.addAttribute("title", "find result");
            model.addAttribute("msg", "find" + name + "'s result!");
            model.addAttribute("value", "");
            List<MyData> list = myDataService.find(name);
            model.addAttribute("datalist", list);
            return "find";
        }
    }

    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public String ajax() {

        return "ajaxpage";
    }
}
