package com.ucm.person.repository;

import com.ucm.person.model.dao.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByName(String name);
}
