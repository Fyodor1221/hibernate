package ru.netology.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.entity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomizedPersonsJpaRepository extends JpaRepository<Person, Long> {
    List<Person> findByCity (String city);

    List<Person> findByBio_AgeLessThanOrderByBio_Age (int age);

    Optional<Person> findByBio_NameAndBio_Surname (String name, String surname);
}
