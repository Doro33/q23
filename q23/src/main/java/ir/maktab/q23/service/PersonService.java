package ir.maktab.q23.service;

import ir.maktab.q23.dao.PersonDao;
import ir.maktab.q23.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    private PersonDao personDao;
    @Transactional
    public Person savePerson(Person person){
       return personDao.save(person);
    }

    public Optional<Person> findById(Long id){
       return personDao.findById(id);
    }

    public void deletePerson(Long id){
        personDao.deleteById(id);
    }
    @Transactional
    public Integer updatePerson(Person person){
        return personDao.updatePerson(person);
    }

    public Optional<Person> findByUsername(String username) {
        return personDao.findByUsername(username);
    }

    public Optional<List<Person>> findByFirstNameAndLastName(String firstName, String lastName){
        return personDao.findByFirstNameAndLastName(firstName,lastName);
    }

    public List<Person> findAll(){
        return personDao.findAll();
    }
}
