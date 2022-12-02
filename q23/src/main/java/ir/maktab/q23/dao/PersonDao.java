package ir.maktab.q23.dao;

import ir.maktab.q23.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonDao extends JpaRepository<Person, Long>, PersonDaoCustom {
    Optional<Person> findByUsername(String username);

    Optional<List<Person>> findByFirstNameAndLastName(String firstName, String lastName);
}
