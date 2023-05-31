package com.iaroslaveremeev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
/**
 * A fencing school trainer (coach) who conducts training for students.
 * Each trainer has an experience measured in the number of years worked in this field.
 */
@Entity
@Table(name = "trainers")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Trainer {
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

    @Column(name = "experience", nullable = false)
    private int experience;

    @OneToOne(mappedBy = "trainer", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private TrainerSchedule trainerSchedule;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Training> trainings;

}

