package ir.maktab.q23.dao.impl;

import ir.maktab.q23.dao.PersonDaoCustom;
import ir.maktab.q23.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class PersonDaoCustomImpl implements PersonDaoCustom {
    @PersistenceContext
    EntityManager em;

    @Override
    public Integer updatePerson(Person person) {
       /*person.setId(id);
       return em.merge(person);*/
        Query query = em.createQuery("""
                                       update Person p
                                       set p.firstName = :fn,
                                       p.lastName = :ln,
                                       p.username = :un,
                                       p.password = :pass,
                                       p.dob = :dob
                                       where p.id =:id       
                """);
        query.setParameter("fn", person.getFirstName());
        query.setParameter("ln", person.getLastName());
        query.setParameter("un", person.getUsername());
        query.setParameter("pass", person.getPassword());
        query.setParameter("dob", person.getDob());
        query.setParameter("id", person.getId());

        return query.executeUpdate();
    }
}
