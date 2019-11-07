package com.ucm.person.service;

import com.google.gson.Gson;
import com.ucm.person.exception.DuplicateRecordException;
import com.ucm.person.exception.InvalidRequestException;
import com.ucm.person.exception.ResourceNotFoundException;
import com.ucm.person.model.dto.Authentication;
import com.ucm.person.model.dto.Person;
import com.ucm.person.model.dto.ResponseBody;
import com.ucm.person.repository.PersonRepository;
import com.ucm.person.transformer.DaoToDtoTransformer;
import com.ucm.person.transformer.DtoToDaoTransformer;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Person validateCreateRequest(Person person) throws URISyntaxException, DuplicateRecordException, InvalidRequestException {


        
        person.setResourceId(UUID.randomUUID());

        URI uri = new URI("http://localhost:8083/authentications");
        Authentication authentication = new Authentication();
        authentication.setUserName(person.getUserName());
        authentication.setPassword(person.getPassword());
        authentication.setPersonResourceId(person.getResourceId());
        authentication.setRole(person.getRole());

        RestTemplate restTemplate = new RestTemplate();

        try {
            restTemplate.postForObject(uri, authentication, Authentication.class);
        }catch ( HttpClientErrorException httpClientErrorException){

            Gson gson=new Gson();
            ResponseBody responseBody = gson.fromJson(httpClientErrorException.getResponseBodyAsString(),ResponseBody.class);

            if(httpClientErrorException.getRawStatusCode()== HttpStatus.CONFLICT.value()){
                throw new DuplicateRecordException(responseBody.getMessage());
            }
            if(httpClientErrorException.getRawStatusCode()== HttpStatus.BAD_REQUEST.value()){
                throw new InvalidRequestException(responseBody.getMessage());
            }
        }

      return DaoToDtoTransformer.DaoToDto(personRepository.save(DtoToDaoTransformer.DtoToDao(person)));

    }
    public Person getResourceId( String resourceId) throws InvalidRequestException, ResourceNotFoundException {

        try{
            UUID.fromString(resourceId);
        }catch (Exception ex){
            throw new InvalidRequestException("Please enter valid resourceId "+resourceId+" entered is Invalid.");
        }

        com.ucm.person.model.dao.Person personDao = personRepository.findByResourceId(UUID.fromString(resourceId));
        if(personDao == null){
            throw new ResourceNotFoundException("person with given "+ resourceId + " doesnt exist");
        }
        return DaoToDtoTransformer.DaoToDto(personDao);

    }

    public Person validateUpdateRequest(String resourceId, Person person) throws InvalidRequestException, ResourceNotFoundException {

        try{
            UUID.fromString(resourceId);
        }catch (Exception ex){
            throw new InvalidRequestException("Please enter valid resourceId " +resourceId+ " entered is Invalid");
        }

        com.ucm.person.model.dao.Person personDao = personRepository.findByResourceId(UUID.fromString(resourceId));

        if(personDao == null){
            throw new ResourceNotFoundException("person with given" + resourceId+ " doesn't exist");
        }
        if(StringUtils.isNotBlank(person.getName())){
             personDao.setName(person.getName());
        }
        if (StringUtils.isNotBlank(person.getAddress())){
            personDao.setAddress(person.getAddress());
        }
        if (StringUtils.isNotBlank(person.getInsurance())){
            personDao.setInsurance(person.getInsurance());
        }
        if(StringUtils.isNotBlank(person.getUserName())){
            personDao.setUserName(person.getUserName());
        }
        if(StringUtils.isNotBlank(person.getPassword())){
            personDao.setPassword(person.getPassword());
        }
        if(person.getCreditCardDetails()!= null){
            if(StringUtils.isNotBlank(person.getCreditCardDetails().getCreditCardNum())){
                personDao.getCreditCardDetails().setCreditCardNum(person.getCreditCardDetails().getCreditCardNum());
            }
        }
        return DaoToDtoTransformer.DaoToDto(personRepository.save(personDao));

    }

}