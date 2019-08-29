package org.crypto.service.impl;

import org.crypto.model.Automobil;
import org.crypto.repo.AutomobilRepository;
import org.crypto.service.AutomobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JpaAutomobilServiceImpl implements AutomobilService {

    @Autowired
    private AutomobilRepository automobilRepository;

    @Override
    public Page<Automobil> findAll(int pageNum) {
        return automobilRepository.findAll(
            new PageRequest(pageNum, 5));
    }

    @Override
    public List<Automobil> findAll() {
        return automobilRepository.findAll();
    }

    @Override
    public Automobil findOne(Long id) {
        return automobilRepository.getOne(id);
    }

    @Override
    public void save(Automobil automobil) {
        automobilRepository.save(automobil);
    }

    @Override
    public Automobil remove(Long id) {

        Automobil automobil = automobilRepository.getOne(id);
        if(automobil != null) {
            automobilRepository.delete(automobil);
        }

        return automobil;
    }

    @Override
    public Page<Automobil> findByKompanijaId(int pageNum, Long kompanijaId) {

        return automobilRepository.findByKompanijaId(kompanijaId, new PageRequest(pageNum, 5));
    }

    @Override
    public Page<Automobil> pretraga(String model, Integer godiste, Double potrosnja, int page) {
        if(model != null ){
            model = "%" + model + "%";
        }
        return automobilRepository.pretraga(model, godiste, potrosnja, new PageRequest(page, 5));
    }

}

