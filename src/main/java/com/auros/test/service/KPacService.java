package com.auros.test.service;

import com.auros.test.model.Pac;

import java.util.List;

public interface KPacService {

    Pac findById(long id);

    List<Pac> findAll();

    List<Pac> findPacsBySet(long id);

    void deleteById(long id);

    void create(Pac pac);
}

