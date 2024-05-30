package ru.netology.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bio implements Serializable {
    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String surname;

    @Column(nullable = false)
    private int age;

    @Override
    public String toString() {
        return name + ", " + surname + ", " + age + ", ";
    }
}
