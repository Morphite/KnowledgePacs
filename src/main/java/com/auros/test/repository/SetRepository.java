package com.auros.test.repository;

import com.auros.test.model.Set;

import java.util.List;

public interface SetRepository {

    Set findById(long id);

    List<Set> findAll();

    void deleteById(long id);

    long create(Set set);

    void addPacsToSet(long idPac, long idSet);
}
