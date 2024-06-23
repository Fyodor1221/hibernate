package ru.netology.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.entity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomizedPersonsJpaRepository extends JpaRepository<Person, Long> {
    @Query("select p from Persons p where p.city = :city")
    List<Person> findByCity (String city);

    @Query("select p from Persons p where p.bio.age < :age")
    List<Person> findByBio_AgeLessThanOrderByBio_Age (int age);

    @Query("select p from Persons  p where p.bio.name = :name and p.bio.surname = :surname")
    Optional<Person> findByBio_NameAndBio_Surname (String name, String surname);
}
