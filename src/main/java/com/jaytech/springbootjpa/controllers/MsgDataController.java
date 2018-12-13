package com.jaytech.springbootjpa.controllers;

import com.jaytech.springbootjpa.dao.MsgDataDaoImpl;
import com.jaytech.springbootjpa.domain.MsgData;
import com.jaytech.springbootjpa.repositories.MsgDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MsgDataController {

    @Autowired
    private MsgDataRepository msgDataRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private MsgDataDaoImpl dao;

    @RequestMapping(value = "msg", method = RequestMethod.GET)
    public String msg(Model model) {

        model.addAttribute("title", "Sample");
        model.addAttribute("msg", "Msg Data");
        MsgData msgData = new MsgData();
        model.addAttribute("formModel", msgData);
        List<MsgData> list = dao.getAll();

        model.addAttribute("datalist", list);
        return "showMsgData";
    }

    @RequestMapping(value = "msg", method = RequestMethod.POST)
    public String msgRequest(@Valid @ModelAttribute MsgData msgData, Model model, Errors result) {

        if(result.hasErrors()) {
            model.addAttribute("title", "ERROR!!!");
            model.addAttribute("msg", "값을 다시 확인하세요");
            return "showMsgData";

        } else {
            System.out.println(msgData);
            msgDataRepository.save(msgData);
            return "redirect:/msg";
        }
    }

    @PostConstruct
    public void init() {
        dao = new MsgDataDaoImpl(entityManager);
    }

}
