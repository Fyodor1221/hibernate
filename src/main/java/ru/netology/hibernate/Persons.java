package ru.netology.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persons {
    @EmbeddedId
    private Bio bio;

    @Column(nullable = false, length = 12)
    private String phone_number;

    @Column(nullable = false, length = 100)
    private String city_of_living;

    @Override
    public String toString() {
        return bio + phone_number + ", " + city_of_living;
    }
}

