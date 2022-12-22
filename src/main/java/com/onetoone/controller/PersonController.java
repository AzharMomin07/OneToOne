package com.onetoone.controller;

import com.onetoone.entity.PersonEntity;
import com.onetoone.model.PersonRequest;
import com.onetoone.model.PersonResponse;
import com.onetoone.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService ;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @PostMapping(path = "/persons",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonRequest personRequest) {
        PersonResponse person = personService.createPerson(personRequest);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
    @GetMapping(path = "/persons/{personId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> getPerson(@PathVariable Long personId){
        PersonResponse person = personService.getPerson(personId);
        return new ResponseEntity<>(person,HttpStatus.OK);
    }
    @PutMapping(path = "/persons/{personId}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonRequest> updatePerson(@RequestBody PersonRequest personRequest , @PathVariable Long personId){
        PersonRequest personRequest1 = personService.updatePerson(personRequest, personId);
        return new ResponseEntity<>(personRequest1,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/persons/{personId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletePerson(@PathVariable Long personId){
        personService.deletePerson(personId);
        return  new ResponseEntity<>("deleted successfully",HttpStatus.OK) ;
    }
    @GetMapping(path = "/persons" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonRequest>>getAllPerson(){
        List<PersonRequest> allPerson = personService.getAllPerson();
        return new ResponseEntity<>(allPerson,HttpStatus.OK) ;
    }
}