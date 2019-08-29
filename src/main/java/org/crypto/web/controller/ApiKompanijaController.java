package org.crypto.web.controller;

import org.crypto.model.Automobil;
import org.crypto.model.Kompanija;
import org.crypto.service.AutomobilService;
import org.crypto.service.KompanijaService;
import org.crypto.support.AutomobilToAutomobilDTO;
import org.crypto.support.KompanijaToKompanijaDTO;
import org.crypto.web.dto.AutomobilDTO;
import org.crypto.web.dto.KompanijaDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/kompanije")
@CrossOrigin(origins = "http://localhost:4200")
public class ApiKompanijaController {

    private KompanijaService kompanijaService;
    private AutomobilService automobilService;
    private KompanijaToKompanijaDTO toDTO;
    private AutomobilToAutomobilDTO toAutomobilDTO;

    public ApiKompanijaController(KompanijaService kompanijaService, AutomobilService automobilService, KompanijaToKompanijaDTO toDTO, AutomobilToAutomobilDTO toAutomobilDTO) {
        this.kompanijaService = kompanijaService;
        this.automobilService = automobilService;
        this.toDTO = toDTO;
        this.toAutomobilDTO = toAutomobilDTO;
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<KompanijaDTO>> get(){
        return new ResponseEntity<>(
            toDTO.convert(kompanijaService.findAll()),
            HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<KompanijaDTO> get(
        @PathVariable Long id){

        Kompanija kompanija = kompanijaService.findOne(id);

        if(kompanija == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
            toDTO.convert(kompanija),
            HttpStatus.OK);
    }

    @RequestMapping(value="/{komoanijaId}/automobili")
    public ResponseEntity<List<AutomobilDTO>> knjigeIzdavac(
        @PathVariable Long kompanijaId,
        @RequestParam(defaultValue="0") int pageNum){
        Page<Automobil> automobili = automobilService.findByKompanijaId(pageNum, kompanijaId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(automobili.getTotalPages()) );
        return  new ResponseEntity<>(
            toAutomobilDTO.convert(automobili.getContent()),
            headers,
            HttpStatus.OK);
    }
}

