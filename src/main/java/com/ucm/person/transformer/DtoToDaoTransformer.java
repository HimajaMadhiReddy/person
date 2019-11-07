package com.ucm.person.transformer;

import com.ucm.person.model.dao.CreditCardDetails;
import com.ucm.person.model.dto.Person;

public class DtoToDaoTransformer {

    public static com.ucm.person.model.dao.Person DtoToDao(Person person ){

        com.ucm.person.model.dao.Person personDao = new com.ucm.person.model.dao.Person();

        personDao.setName(person.getName());
        personDao.setAddress(person.getAddress());
        personDao.setInsurance(person.getInsurance());
        personDao.setUserName(person.getUserName());
        personDao.setResourceId(person.getResourceId());
        personDao.setPassword(person.getPassword());
        personDao.setRole(person.getRole());
        personDao.setCreditCardDetails(DtoToDao(person.getCreditCardDetails()));

        return personDao;


    }
    public static CreditCardDetails DtoToDao(com.ucm.person.model.dto.CreditCardDetails creditCardDetails){

        CreditCardDetails creditCardDetailsDao = new CreditCardDetails();
        creditCardDetailsDao.setCreditCardNum(creditCardDetails.getCreditCardNum());
        creditCardDetailsDao.setCvvNumber(creditCardDetails.getCvvNumber());
        creditCardDetailsDao.setExpirationDate(creditCardDetails.getExpirationDate());

        return creditCardDetailsDao;

    }
}
