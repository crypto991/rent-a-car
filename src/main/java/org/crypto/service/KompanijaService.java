package org.crypto.service;

import org.crypto.model.Kompanija;

import java.util.List;

public interface KompanijaService {

    List<Kompanija> findAll();

    Kompanija findOne(Long id);

    void save(Kompanija kompanija);

    void remove(Long id);
}
