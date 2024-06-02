package ru.netology.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PersonsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List getPersonsByCity(String city) {
        return entityManager.createQuery("SELECT p FROM Persons AS p WHERE p.city_of_living like :city")
                .setParameter("city", city.toUpperCase())
                .getResultList();
    }
}
