package com.example.personmicroservice.Repository;

import com.example.personmicroservice.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findPersonById(Integer id);
}
