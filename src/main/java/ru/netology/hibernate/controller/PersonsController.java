package ru.netology.hibernate.controller;

import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Secured({"ROLE_READ"})
    public List<Person> getPersonsByCity(@RequestParam("city") String city) {
        return customizedPersonsJpaRepository.findByCity(city);
    }

    @GetMapping("/persons/all")
    @PostAuthorize("#username == authentication.principal.username")
    public List<Person> getAllPersons(@RequestParam String username) {
        return customizedPersonsJpaRepository.findAll();
    }

    @PostMapping("/persons/save")
    @RolesAllowed("ROLE_WRITE")
    public Person savePerson(
            @RequestBody String name,
            @RequestBody String surname,
            @RequestBody String age,
            @RequestBody String city,
            @RequestBody String phone)
    {
        Person person = new Person(
                new Bio(name, surname, Integer.parseInt(age)),
                phone,
                city);
        return customizedPersonsJpaRepository.save(person);
    }

    @GetMapping("/persons/by-age")
    @RolesAllowed("ROLE_READ")
    public List<Person> getPersonsByAge(@RequestParam("age") String ageStr) {
        int age = Integer.parseInt(ageStr);
        return customizedPersonsJpaRepository.findByBio_AgeLessThanOrderByBio_Age(age);
    }

    @GetMapping("/persons/by-name-surname")
    @PreAuthorize("hasRole('READ')")
    public List<Person> getPersonsByNameAndSurname(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname)
    {
        List<Person> persons = new ArrayList<>();
        customizedPersonsJpaRepository.findByBio_NameAndBio_Surname(name, surname)
                .ifPresentOrElse(persons::add, () -> System.out.println("По запросу ничего не надено"));
        return  persons;
    }

    @DeleteMapping("/persons/delete")
    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
    public String deletePerson(
            @RequestBody String name,
            @RequestBody String surname,
            @RequestBody String age)
    {
        customizedPersonsJpaRepository.deleteByBio_NameAndBio_SurnameAndBio_Age(name, surname, Integer.parseInt(age));
        return String.format("Пользователь %s %s %s лет удален", name, surname, age);
    }
}
