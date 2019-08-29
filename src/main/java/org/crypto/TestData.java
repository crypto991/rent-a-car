package org.crypto;

import org.crypto.model.Automobil;
import org.crypto.model.Kompanija;
import org.crypto.service.AutomobilService;
import org.crypto.service.KompanijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestData {

    @Autowired
    private KompanijaService kompanijaService;

    @Autowired
    private AutomobilService automobilService;

    public TestData() {
    }

    @PostConstruct
    public void init() {

        Kompanija k1 = new Kompanija();
        k1.setNaziv("SuRent");
        k1.setAdresa("Balzakova 1");
        k1.setTelefon("024/151-363");
        kompanijaService.save(k1);

        Kompanija k2 = new Kompanija();
        k2.setNaziv("NSRent");
        k2.setAdresa("Maksima Gorkog 2");
        k2.setTelefon("021/4141-515");
        kompanijaService.save(k2);

        Automobil a1 = new Automobil();
        a1.setModel("Nissan Prairie");
        a1.setRegistracija("SU82404");
        a1.setGodiste(1991);
        a1.setPotrosnja(10.3);
        a1.setKompanija(k1);

        automobilService.save(a1);
    }
}
