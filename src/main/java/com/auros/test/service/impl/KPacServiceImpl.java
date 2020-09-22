package com.auros.test.service.impl;

import com.auros.test.model.Pac;
import com.auros.test.repository.KPacRepository;
import com.auros.test.service.KPacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KPacServiceImpl implements KPacService {

    @Autowired
    private KPacRepository repository;

    @Override
    public Pac findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Pac> findAll() {
        List<Pac> pacsWithIdOnly = repository.findAll();

        return fillPacsWithDeleteHtmlField(pacsWithIdOnly);
    }

    @Override
    public List<Pac> findPacsBySet(long id) {
        List<Pac> pacsWithIdOnly = repository.findPacsBySet(id);

        return fillPacsWithDeleteHtmlField(pacsWithIdOnly);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void create(Pac pac) {
        repository.create(pac);
    }

    private List<Pac> fillPacsWithDeleteHtmlField(List<Pac> pacsWithIdOnly) {
        List<Pac> filledPacs = new ArrayList<>();

        for (Pac pac : pacsWithIdOnly) {
            Pac pacWithoutDeleteField = findById(pac.getId_pac());
            pacWithoutDeleteField.setDeleteFieldWithHtml("<section class=\"dhx_sample-controls\">\n" +
                    "        <button class=\"mar dhx_sample-btn dhx_sample-btn--flat\"><a href=\"/kpacs/delete/" + pac.getId_pac() + "\">Delete</a></button>\n" +
                    "    </section>");
            filledPacs.add(pacWithoutDeleteField);
        }
        return filledPacs;
    }
}
