package com.ucm.person.transformer;

import com.ucm.person.model.dto.CreditCardDetails;
import com.ucm.person.model.dto.Person;

public class DaoToDtoTransformer {

    public static Person DaoToDto(com.ucm.person.model.dao.Person person){

        Person persondto = new Person();
        persondto.setName(person.getName());
        persondto.setAddress(person.getAddress());
        persondto.setInsurance(person.getInsurance());
        persondto.setResourceId(person.getResourceId());
        persondto.setUserName(person.getUserName());
        persondto.setPassword(person.getPassword());
        persondto.setRole(person.getRole());
        persondto.setCreditCardDetails(DaoToDto(person.getCreditCardDetails()));

        return persondto;

    }

    public static CreditCardDetails DaoToDto(com.ucm.person.model.dao.CreditCardDetails creditCardDetails ){

        CreditCardDetails creditCardDetailsDto = new CreditCardDetails();
        creditCardDetailsDto.setCreditCardNum(creditCardDetails.getCreditCardNum());
        creditCardDetailsDto.setCvvNumber(creditCardDetails.getCvvNumber());
        creditCardDetailsDto.setExpirationDate(creditCardDetails.getExpirationDate());

        return  creditCardDetailsDto;
    }

}

