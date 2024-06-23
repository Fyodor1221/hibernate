package ru.netology.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "Persons")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @EmbeddedId
    private Bio bio;

    @Column(nullable = false, length = 12)
    private String phone;

    @Column(nullable = false, length = 100)
    private String city;
}
