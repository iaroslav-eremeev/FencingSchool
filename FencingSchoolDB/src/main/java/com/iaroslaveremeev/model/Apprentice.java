package com.iaroslaveremeev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Fencing school student who attends training sessions with different coaches
 */
@Entity
@Table(name = "apprentices")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Apprentice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "surname", nullable = false)
    @NonNull
    private String surname;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "phone_number", nullable = false)
    @NonNull
    private String phoneNumber;

    @OneToMany(mappedBy = "apprentice", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Training> trainings;
}
