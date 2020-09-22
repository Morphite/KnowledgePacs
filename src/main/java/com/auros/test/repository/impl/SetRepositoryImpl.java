package com.auros.test.repository.impl;

import com.auros.test.model.Set;
import com.auros.test.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class SetRepositoryImpl implements SetRepository {

    @Autowired
    private JdbcTemplate jt;

    @Override
    public Set findById(long id) {
        return jt.queryForObject("SELECT * FROM k_pac_set WHERE id_set  = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Set.class));
    }

    @Override
    public void addPacsToSet(long idPac, long idSet) {
        jt.update("INSERT INTO k_pac_to_set VALUES (?, ?)", idPac, idSet);
    }

    @Override
    public List<Set> findAll() {
        return jt.query("SELECT * FROM k_pac_set", new BeanPropertyRowMapper<>(Set.class));
    }

    @Override
    public void deleteById(long id) {
        jt.update("DELETE FROM k_pac_set WHERE id_set = ?", id);
    }

    @Override
    public long create(Set set) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jt.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO k_pac_set (title) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, set.getTitle());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
