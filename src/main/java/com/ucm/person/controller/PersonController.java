package com.ucm.person.controller;


import com.ucm.person.exception.DuplicateRecordException;
import com.ucm.person.exception.InvalidRequestException;
import com.ucm.person.model.dto.Person;
import com.ucm.person.repository.PersonRepository;
import com.ucm.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonService personService;

//    @RequestMapping(value = "/{name}",produces = "application/json")
//    public @ResponseBody
//    List<com.ucm.person.model.dao.Person> getPerson(@PathVariable String name) {
//       return personRepository.findByName(name);
//    }

    @RequestMapping(method = POST,produces="application/json",consumes = "application/json")
    @ResponseBody
    com.ucm.person.model.dao.Person createPerson(@RequestBody Person person) throws InvalidRequestException, DuplicateRecordException {

        return personService.validateCreateRequest(person);
    }



}
