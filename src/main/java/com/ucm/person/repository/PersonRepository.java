package com.ucm.person.repository;

import com.ucm.person.model.dao.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PersonRepository extends MongoRepository<Person, String> {

    Person findByResourceId(UUID resourceId);

}
