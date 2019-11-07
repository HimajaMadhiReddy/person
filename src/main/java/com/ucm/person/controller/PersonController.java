package com.ucm.person.controller;


import com.ucm.person.exception.DuplicateRecordException;
import com.ucm.person.exception.InvalidRequestException;
import com.ucm.person.exception.ResourceNotFoundException;
import com.ucm.person.model.dto.Person;
import com.ucm.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonService personService;


    @RequestMapping(method = RequestMethod.POST,produces="application/json",consumes = "application/json")
    @ResponseBody
   Person createPerson(@RequestBody Person person) throws URISyntaxException, DuplicateRecordException, InvalidRequestException {

        return personService.validateCreateRequest(person);
    }

    @ResponseBody
    @RequestMapping(value = "/{resourceId}",produces = "application/json",method = RequestMethod.GET)
   Person getByResourceId(@PathVariable String resourceId) throws InvalidRequestException, ResourceNotFoundException {

        return personService.getResourceId(resourceId);


   }
    @ResponseBody
    @RequestMapping(value = "/{resourceId}",method = RequestMethod.PUT, produces = "application/json")

    Person updatePerson(@PathVariable String resourceId,@RequestBody Person person) throws InvalidRequestException, ResourceNotFoundException {

        return personService.validateUpdateRequest(resourceId, person);

    }

}
