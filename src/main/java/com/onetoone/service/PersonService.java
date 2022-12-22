package com.onetoone.service;

import com.onetoone.entity.AddressEntity;
import com.onetoone.entity.PersonEntity;
import com.onetoone.model.AddressRequest;
import com.onetoone.model.PersonRequest;
import com.onetoone.model.PersonResponse;
import com.onetoone.repository.AddressEntityRepository;
import com.onetoone.repository.PersonEntityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PersonService {

    private final PersonEntityRepository personEntityRepository;

    private final AddressEntityRepository addressEntityRepository;

    @Autowired
    public PersonService(PersonEntityRepository personEntityRepository, AddressEntityRepository addressEntityRepository) {
        this.personEntityRepository = personEntityRepository;
        this.addressEntityRepository = addressEntityRepository;
    }

    public PersonResponse createPerson(PersonRequest personRequest) {

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddress1(personRequest.getAddress().getAddress1());
        addressEntity.setAddress2(personRequest.getAddress().getAddress2());
        addressEntity.setState(personRequest.getAddress().getState());
        addressEntity.setCity(personRequest.getAddress().getCity());
        addressEntityRepository.save(addressEntity);

        PersonEntity personEntity = new PersonEntity();

        personEntity.setFirstName(personRequest.getFirstName());
        personEntity.setLastName(personRequest.getLastName());
        personEntity.setAddress(addressEntity);

        personEntityRepository.save(personEntity);

        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(personEntity.getId());

        return personResponse;


    }

    public PersonRequest getPerson(Long personId) {
        PersonEntity personEntity = personEntityRepository.findById(personId).get();
        AddressEntity addressEntity = addressEntityRepository.findById(personEntity.getAddress().getId()).get();
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddress1(addressEntity.getAddress1());
        addressRequest.setAddress2(addressEntity.getAddress2());
        addressRequest.setCity(addressEntity.getCity());
        addressRequest.setState(addressEntity.getState());

        PersonRequest personRequest = new PersonRequest();

        personRequest.setId(personEntity.getId());
        personRequest.setFirstName(personEntity.getFirstName());
        personRequest.setLastName(personEntity.getLastName());
        personRequest.setAddress(addressRequest);


        return personRequest;
    }


    public PersonRequest updatePerson(PersonRequest personRequest, Long personId) {
        PersonEntity personEntity = personEntityRepository.findById(personId).get();
        AddressEntity addressEntity = addressEntityRepository.findById(personEntity.getAddress().getId()).get();


        addressEntity.setAddress1(personRequest.getAddress().getAddress1());
        addressEntity.setAddress2(personRequest.getAddress().getAddress2());
        addressEntity.setCity(personRequest.getAddress().getCity());
        addressEntity.setState(personRequest.getAddress().getState());

        addressEntityRepository.save(addressEntity);

        personEntity.setFirstName(personRequest.getFirstName());
        personEntity.setLastName(personRequest.getLastName());
        personEntity.setAddress(addressEntity);
        personEntityRepository.save(personEntity);

        return personRequest;

    }

    public void deletePerson(Long personId) {
        PersonEntity personEntity = personEntityRepository.findById(personId).get();
        personEntityRepository.deleteById(personId);
        addressEntityRepository.deleteById(personEntity.getAddress().getId());

    }


    public List<PersonRequest> getAllPerson() {
        List<PersonEntity> personEntities = personEntityRepository.findAll();
        List<PersonRequest> personRequests = new ArrayList<>(personEntities.size());

        for (PersonEntity personEntity : personEntities) {


            AddressRequest addressRequest = new AddressRequest();
            addressRequest.setAddress1(personEntity.getAddress().getAddress1());
            addressRequest.setAddress2(personEntity.getAddress().getAddress2());
            addressRequest.setCity(personEntity.getAddress().getCity());
            addressRequest.setState(personEntity.getAddress().getState());
            PersonRequest personRequest = new PersonRequest();
            personRequest.setId(personEntity.getId());
            personRequest.setFirstName(personEntity.getFirstName());
            personRequest.setLastName(personEntity.getLastName());
            personRequest.setAddress(addressRequest);
            personRequests.add(personRequest);

        }


        return personRequests;
    }
}




