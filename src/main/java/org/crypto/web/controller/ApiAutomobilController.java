package org.crypto.web.controller;


import org.crypto.model.Automobil;
import org.crypto.model.Najam;
import org.crypto.service.AutomobilService;
import org.crypto.service.NajamService;
import org.crypto.support.AutomobilDTOToAutomobil;
import org.crypto.support.AutomobilToAutomobilDTO;
import org.crypto.support.NajamToNajamDTO;
import org.crypto.web.dto.AutomobilDTO;
import org.crypto.web.dto.NajamDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automobili")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = {"totalPages", "totalElements"})
public class ApiAutomobilController {

    private AutomobilService automobilService;
    private AutomobilToAutomobilDTO toAutomobilDTO;
    private NajamToNajamDTO toNajamDTO;
    private AutomobilDTOToAutomobil toAutomobil;
    private NajamService najamService;

    public ApiAutomobilController(AutomobilService automobilService, AutomobilToAutomobilDTO toAutomobilDTO, NajamToNajamDTO toNajamDTO, AutomobilDTOToAutomobil toAutomobil, NajamService najamService) {
        this.automobilService = automobilService;
        this.toAutomobilDTO = toAutomobilDTO;
        this.toNajamDTO = toNajamDTO;
        this.toAutomobil = toAutomobil;
        this.najamService = najamService;
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<AutomobilDTO>> get(
        @RequestParam(required=false) String model,
        @RequestParam(required=false) Integer godiste,
        @RequestParam(required=false) Double potrosnja,
        @RequestParam(defaultValue="0") int pageNum){

        Page<Automobil> automobili;

        if(model != null || godiste != null || potrosnja != null) {
            automobili = automobilService.pretraga(model, godiste, potrosnja, pageNum);
        }else{
            automobili = automobilService.findAll(pageNum);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(automobili.getTotalPages()));
        headers.add("totalElements", Long.toString(automobili.getTotalElements()));
        return  new ResponseEntity<>(
            toAutomobilDTO.convert(automobili.getContent()),
            headers,
            HttpStatus.OK);
    }


    @RequestMapping(method=RequestMethod.GET,
        value="/{id}")
    public ResponseEntity<AutomobilDTO> get(
        @PathVariable Long id){
        Automobil automobil = automobilService.findOne(id);

        if(automobil==null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
            toAutomobilDTO.convert(automobil),
            HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<AutomobilDTO> add(
        @Validated @RequestBody AutomobilDTO novAutomobil){

        Automobil automobil = toAutomobil.convert(novAutomobil);
        automobilService.save(automobil);

        return new ResponseEntity<>(toAutomobilDTO.convert(automobil),
            HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.PUT,
        value="/{id}")
    public ResponseEntity<AutomobilDTO> edit(
        @PathVariable Long id,
        @Validated @RequestBody AutomobilDTO izmenjen){

        if(!id.equals(izmenjen.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Automobil automobil = toAutomobil.convert(izmenjen);
        automobilService.save(automobil);

        return new ResponseEntity<>(toAutomobilDTO.convert(automobil),
            HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.DELETE,
        value="/{id}")
    public ResponseEntity<AutomobilDTO> delete(@PathVariable Long id){

        Automobil deleted = automobilService.remove(id);
        if(deleted == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
            toAutomobilDTO.convert(deleted),
            HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, value="/{id}/iznajmljivanje")
    public ResponseEntity<NajamDTO> rent(@PathVariable Long id){

        Najam n = najamService.rentACar(id);

        if(n != null) {
            return new ResponseEntity<>(toNajamDTO.convert(n), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler
    public ResponseEntity<Void> validationHandler(
        DataIntegrityViolationException e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
