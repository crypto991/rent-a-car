package org.crypto.service;

import org.crypto.model.Automobil;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutomobilService {

   Page<Automobil> findAll(int pageNum);

   List<Automobil> findAll();

    Automobil findOne(Long id);

   void save(Automobil automobil);

   Automobil remove(Long id);

   Page<Automobil> findByKompanijaId(int pageNum, Long kompanijaId);

   Page<Automobil> pretraga(
       @Param("model") String model,
       @Param("godiste") Integer godiste,
       @Param("potrosnja") Double potrosnja,
       int page);

}
