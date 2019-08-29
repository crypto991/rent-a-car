package org.crypto.support;

import org.crypto.model.Automobil;
import org.crypto.model.Kompanija;
import org.crypto.service.AutomobilService;
import org.crypto.service.KompanijaService;
import org.crypto.web.dto.AutomobilDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AutomobilDTOToAutomobil implements Converter<AutomobilDTO, Automobil> {

    private AutomobilService automobilService;

    private KompanijaService kompanijaService;

    public AutomobilDTOToAutomobil(AutomobilService automobilService, KompanijaService kompanijaService) {
        this.automobilService = automobilService;
        this.kompanijaService = kompanijaService;
    }

    @Override
    public Automobil convert(AutomobilDTO source) {
        Automobil automobil;

        Kompanija k = kompanijaService.findOne(source.getKompanijaId());

        if(source.getId()==null){
            automobil = new Automobil();
        }else{
            automobil = automobilService.findOne(source.getId());
        }

        automobil.setKompanija(k);

        automobil.setModel(source.getModel());
        automobil.setRegistracija(source.getRegistracija());
        automobil.setGodiste(source.getGodiste());
        automobil.setPotrosnja(source.getPotrosnja());
        automobil.setIznajmljen(source.getIznajmljen());

        return automobil;
    }
}
