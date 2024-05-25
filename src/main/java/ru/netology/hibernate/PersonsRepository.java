package ru.netology.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PersonsRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final List<Persons> people = new ArrayList<>();

    public PersonsRepository() {
        for (int i = 0; i < 10; i++) {
            List<String> cities = Arrays.asList("Moscow", "Saint-Petersburg", "Ekaterinburg", "Kazan", "Kirov");
            List<String> names = Arrays.asList("Ivan", "Fyodor", "Pavel", "Stepan");
            List<String> surnames = Arrays.asList("Ivanov", "Fyodorov", "Pavlov", "Stepanov");
            List<String> phones = Arrays.asList("+1111", "+2222", "+3333", "+4444");
            this.addPerson(
                    names.get(getRandomInt(4)),
                    surnames.get(getRandomInt(4)),
                    getRandomInt(100),
                    phones.get(getRandomInt(4)),
                    cities.get(getRandomInt(4))
                    );
        }
    }

    private int getRandomInt(int i) {
        return (int) (Math.random() * i);
    }

    public void addPerson(String name, String surname, int age, String phone_number, String city_of_living) {
        Bio bio = new Bio(name, surname, age);
        Persons person = new Persons(bio, phone_number, city_of_living);
        this.people.add(person);
    }

    public List<Persons> getPersonsByCity(String city) {
        return this.people.stream().filter(person -> person.getCity_of_living().equals(city)).toList();
    }
}
