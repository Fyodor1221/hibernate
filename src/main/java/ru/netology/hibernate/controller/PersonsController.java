package ru.netology.hibernate.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.hibernate.entity.Bio;
import ru.netology.hibernate.entity.Person;
import ru.netology.hibernate.repository.CustomizedPersonsJpaRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class PersonsController {
    private CustomizedPersonsJpaRepository customizedPersonsJpaRepository;

    @GetMapping("/persons/by-city")
    public List<Person> getPersonsByCity(@RequestParam("city") String city) {
        return customizedPersonsJpaRepository.findByCity(city);
    }

    @PostMapping("/persons/save")
    public void savePerson(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("age") String ageStr,
            @RequestParam("city") String city,
            @RequestParam("phone") String phone)
    {
        int age = Integer.parseInt(ageStr);
        Bio bio = new Bio(name, surname, age);
        Person person = new Person(bio, phone, city);

        customizedPersonsJpaRepository.save(person);
    }

    @GetMapping("/persons/all")
    public List<Person> getAllPersons() {
        return customizedPersonsJpaRepository.findAll();
    }

    @GetMapping("/persons/by-age")
    public List<Person> getPersonsByAge(@RequestParam("age") String ageStr) {
        int age = Integer.parseInt(ageStr);
        return customizedPersonsJpaRepository.findByBio_AgeLessThanOrderByBio_Age(age);
    }

    @GetMapping("/persons/by-name-surname")
    public List<Person> getPersonsByNameAndSurname(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname)
    {
        List<Person> persons = new ArrayList<>();
        customizedPersonsJpaRepository.findByBio_NameAndBio_Surname(name, surname)
                .ifPresentOrElse(persons::add, () -> System.out.println("По запросу ничего не надено"));
        return  persons;
    }
}