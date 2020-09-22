package com.auros.test.repository;

import com.auros.test.model.Pac;
import com.auros.test.model.Set;

import java.util.List;

public interface KPacRepository {

    Pac findById(long id);

    List<Pac> findAll();

    List<Pac> findPacsBySet(long id);

    void deleteById(long id);

    void create(Pac pac);
}
