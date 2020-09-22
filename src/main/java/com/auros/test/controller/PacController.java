package com.auros.test.controller;

import com.auros.test.model.Pac;
import com.auros.test.service.KPacService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PacController {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private KPacService service;

    @GetMapping("/kpacs")
    public String getPacs(Model model) throws JsonProcessingException {
        List<Pac> pacs = service.findAll();

        model.addAttribute("pacs", mapper.writeValueAsString(pacs));
        model.addAttribute("default_date", LocalDate.now());
        return "pacs";
    }

    @PostMapping("/kpacs/create")
    public String createKPac(Pac pac) {
        service.create(pac);
        return "redirect:/kpacs";
    }

    @GetMapping("/kpacs/delete/{pacId}")
    public String deleteKPac(@PathVariable int pacId) {
        service.deleteById(pacId);
        return "redirect:/kpacs";
    }

}
