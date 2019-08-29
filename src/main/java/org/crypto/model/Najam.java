package org.crypto.model;

import javax.persistence.*;

@Entity
public class Najam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Automobil automobil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;

        if(automobil!=null && !automobil.getNajmovi().contains(this)){
            automobil.getNajmovi().add(this);
        }
    }
}
