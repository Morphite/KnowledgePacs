package com.auros.test.repository.impl;

import com.auros.test.model.Pac;
import com.auros.test.model.Set;
import com.auros.test.repository.KPacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KPacRepositoryImpl implements KPacRepository {

    @Autowired
    private JdbcTemplate jt;

    @Override
    public Pac findById(long id) {
        return jt.queryForObject("SELECT * FROM k_pac WHERE id_pac = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Pac.class));
    }

    @Override
    public List<Pac> findAll() {
        return jt.query("SELECT * FROM k_pac", new BeanPropertyRowMapper<>(Pac.class));
    }

    @Override
    public List<Pac> findPacsBySet(long id) {
        return jt.query("SELECT * FROM k_pac_to_set WHERE id_set  = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Pac.class));
    }

    @Override
    public void deleteById(long id) {
        jt.update("DELETE FROM k_pac WHERE id_pac = ?", id);
    }

    @Override
    public void create(Pac pac) {
        jt.update("INSERT INTO k_pac (title, description, creation_date) VALUES (?, ?, ?)", pac.getTitle(), pac.getDescription(), pac.getCreation_date());
    }
}
