package ru.netology.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.entity.Bio;
import ru.netology.hibernate.entity.Person;

import java.util.*;

@Repository
public class PersonsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final List<Bio> bios = new ArrayList<>();
    private final List<Person> persons = new ArrayList<>();

    public PersonsRepository() {
        createDataBase();
    }

    private void createDataBase() {
        bios.add(Bio.builder().name("Ivan").surname("Ivanov").age(17).build());
        bios.add(Bio.builder().name("Fyodor").surname("Fyodorov").age(27).build());
        bios.add(Bio.builder().name("Pavel").surname("Pavlov").age(37).build());
        bios.add(Bio.builder().name("Stepan").surname("Stepanov").age(47).build());

        persons.add(Person.builder().bio(bios.get(0)).phone_number("1111").city_of_living("Moscow").build());
        persons.add(Person.builder().bio(bios.get(1)).phone_number("2222").city_of_living("Saint-Petersburg").build());
        persons.add(Person.builder().bio(bios.get(2)).phone_number("3333").city_of_living("Ekaterinburg").build());
        persons.add(Person.builder().bio(bios.get(3)).phone_number("4444").city_of_living("Kazan").build());

        persons.forEach(entityManager::persist);
    }

    public List getPersonsByCity(String city) {
        persons.forEach(entityManager::persist);
        return entityManager.createQuery("SELECT p FROM Persons AS p WHERE p.city_of_living = :city")
                .setParameter(city, city.toLowerCase())
                .getResultList();
    }
}
