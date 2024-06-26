package ru.netology.hibernate;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonsController {
    private final PersonsRepository repository;

    @GetMapping("/persons/by-city")
    public List getPersons(@RequestParam("city") String city) {
        repository.createDataBase();
        return repository.getPersonsByCity(city);
    }
}
