package com.auros.test.controller;

import com.auros.test.model.Pac;
import com.auros.test.model.Set;
import com.auros.test.service.KPacService;
import com.auros.test.service.SetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SetController {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SetService setService;

    @Autowired
    private KPacService kPacService;

    @GetMapping("/sets")
    public String getSets(Model model) throws JsonProcessingException {
        List<Set> sets = setService.findAll();

        model.addAttribute("sets", mapper.writeValueAsString(sets));
        model.addAttribute("pacs", kPacService.findAll());
        return "sets";
    }

    @GetMapping("/sets/create")
    public String getKPacCreateForm(Model model) {
        model.addAttribute("pacs", kPacService.findAll());
        return "createSet";
    }

    @PostMapping("/sets/create")
    public String createKPac(Set set, Integer... idPacs) {
        long generatedSetKey = setService.create(set);
        setService.addPacsToSet(generatedSetKey, idPacs);
        return "redirect:/sets";
    }

    @GetMapping("/sets/delete/{setId}")
    public String deleteKPac(@PathVariable int setId) {
        setService.deleteById(setId);
        return "redirect:/sets";
    }

    @GetMapping("/set/{setId}")
    public String setWithKPacs(@PathVariable int setId, Model model) throws JsonProcessingException {
        List<Pac> pacs = kPacService.findPacsBySet(setId);

        model.addAttribute("pacs", mapper.writeValueAsString(pacs));
        return "setWithKPacs";
    }

}
