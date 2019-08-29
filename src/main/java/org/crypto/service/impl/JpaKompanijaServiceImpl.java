package org.crypto.service.impl;


import org.crypto.model.Kompanija;
import org.crypto.repo.KompanijaRepository;
import org.crypto.service.KompanijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JpaKompanijaServiceImpl implements KompanijaService {
    @Autowired
    private KompanijaRepository kompanijaRepository;

    @Override
    public List<Kompanija> findAll() {
        return kompanijaRepository.findAll();
    }

    @Override
    public Kompanija findOne(Long id) {
        return kompanijaRepository.getOne(id);
    }

    @Override
    public void save(Kompanija kompanija) {
        kompanijaRepository.save(kompanija);
    }

    @Override
    public void remove(Long id) {
        kompanijaRepository.deleteById(id);
    }
}
