package com.auros.test.service.impl;

import com.auros.test.model.Set;
import com.auros.test.repository.SetRepository;
import com.auros.test.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetServiceImpl implements SetService {

    @Autowired
    private SetRepository setRepository;

    @Override
    public Set findById(long id) {
        return setRepository.findById(id);
    }

    @Override
    public List<Set> findAll() {
        List<Set> sets = setRepository.findAll();

        for (Set set : sets) {
            set.setDeleteFieldWithHml("<section class=\"dhx_sample-controls\">\n" +
                    "<button class=\"mar dhx_sample-btn dhx_sample-btn--flat\"><a href=\"/sets/delete/" + set.getId_set() + "\">Delete</a></button>\n" +
                    "</section>");
        }
        return sets;
    }

    @Override
    public void deleteById(long id) {
        setRepository.deleteById(id);
    }

    @Override
    public long create(Set set) {
        return setRepository.create(set);
    }

    @Override
    public void addPacsToSet(long idSet, Integer... idPacs) {
        for (Integer idPac : idPacs) {
            setRepository.addPacsToSet(idPac, idSet);
        }
    }
}
