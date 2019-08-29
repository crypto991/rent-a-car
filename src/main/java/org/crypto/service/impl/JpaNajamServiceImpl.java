package org.crypto.service.impl;

import org.crypto.model.Automobil;
import org.crypto.model.Najam;
import org.crypto.repo.AutomobilRepository;
import org.crypto.repo.NajamRepository;
import org.crypto.service.NajamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaNajamServiceImpl implements NajamService {

    @Autowired
    private NajamRepository najamRepository;
    @Autowired
    private AutomobilRepository automobilRepository;

    @Override
    public Najam rentACar(Long automobilId) {

        if(automobilId == null) {
            throw new IllegalArgumentException("Id of a car cannot be null!");
        }

        Automobil automobil = automobilRepository.getOne(automobilId);
        if(automobil == null) {
            throw new IllegalArgumentException("There is no car with given id!");
        }

        if(!automobil.getIznajmljen()) {
            automobil.setIznajmljen(true);

            Najam najam = new Najam();
            najam.setAutomobil(automobil);

            najamRepository.save(najam);
            automobilRepository.save(automobil);

            return najam;
        }

        return null;
    }
}
