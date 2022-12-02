package ir.maktab.q23.service;

import ir.maktab.q23.config.AppConfig;
import ir.maktab.q23.entity.Person;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringJUnitConfig(value = AppConfig.class)
@ActiveProfiles("test")
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonServiceTest {
    @Autowired
    PersonService personService;

    @Test
    @Order(1)
    void savePerson() {
        Person  person =
                new Person(null, "Arash", "Mohamadi", "ara", "00", LocalDate.now());
        personService.savePerson(person);
        Optional<Person> actualPerson=personService.findById(person.getId());
        actualPerson.ifPresent(value -> assertEquals(value, person));
    }

    @Test
    @Order(2)
    void findById() {
        Person  person =
                new Person(null, "Arash", "Mohamadi", "ara", "00", LocalDate.now());
        personService.savePerson(person);
        Optional<Person> actualPerson=personService.findById(person.getId());
        actualPerson.ifPresent(value -> assertEquals(value, person));
    }

    @Test
    @Order(3)
    void deletePerson() {
        Person  person =
                new Person(null, "Arash", "Mohamadi", "ara", "00", LocalDate.now());
        Person savedPerson = personService.savePerson(person);
        Long savedId = savedPerson.getId();

        personService.deletePerson(savedId);

        Optional<Person> fetchedPerson=personService.findById(savedId);

        assertEquals(Optional.empty(),fetchedPerson);
    }

    @Test
    void updatePerson() {
        Person  person =
                new Person(null, "Arash", "Mohamadi", "ara", "00", LocalDate.now());
        Person savedPerson = personService.savePerson(person);
        savedPerson.setFirstName("mamad");
        personService.updatePerson(person);
        //personService.savePerson(person);
        Optional<Person> fetchedPerson =personService.findById(savedPerson.getId());
        fetchedPerson.ifPresent(value -> assertEquals(savedPerson, value));
    }

    @Test
    void findByUsername() {
        String username = "ara";
        Person  person =
                new Person(null, "Arash", "Mohamadi", username, "00", LocalDate.now());
        Person savedPerson = personService.savePerson(person);

        Optional<Person> fetchedPerson = personService.findByUsername(username);
        fetchedPerson.ifPresent(value -> assertEquals(savedPerson, value));
    }

    @Test
    void findByFirstNameAndLastName() {
        Person person =
                new Person(null, "Arash", "Mohamadi", "ara", "00", LocalDate.now());
        Person savedPerson = personService.savePerson(person);
        Person savedPerson2 = personService.savePerson(person);
        Optional<List<Person>> fetchedPerson = personService.findByFirstNameAndLastName("Arash", "Mohamadi");
        if (fetchedPerson.isPresent()) {
            assertTrue(fetchedPerson.get().contains(savedPerson));
            assertEquals(personService.findAll().size()
                    , fetchedPerson.get().size());
        }
    }
}