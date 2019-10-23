package com.ucm.person.service;

import com.ucm.person.exception.DuplicateRecordException;
import com.ucm.person.exception.InvalidRequestException;
import com.ucm.person.model.dto.Person;
import com.ucm.person.repository.PersonRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public com.ucm.person.model.dao.Person validateCreateRequest(Person person) throws InvalidRequestException, DuplicateRecordException {
     //isBlank is used to check both null and empty
        if(StringUtils.isBlank(person.getName())){
         throw new InvalidRequestException("Please enter name.");
        }


        List<com.ucm.person.model.dao.Person> personList = new ArrayList<>();
        personList = personRepository.findAll();

        for (com.ucm.person.model.dao.Person  person2 : personList){
        }

        com.ucm.person.model.dao.Person person1 = new com.ucm.person.model.dao.Person();
        person1.setName(person.getName());
        return personRepository.save(person1);

    }

}
