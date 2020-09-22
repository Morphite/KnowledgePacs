package com.auros.test.service;

import com.auros.test.model.Set;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface SetService {

    Set findById(long id);

    List<Set> findAll();

    void deleteById(long id);

    long create(Set set);

    void addPacsToSet(long idSet, Integer... idPacs);
}
