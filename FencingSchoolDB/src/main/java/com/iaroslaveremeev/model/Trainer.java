package com.iaroslaveremeev.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
    private int id;

    @Column(name = "surname", nullable = false)
    @NonNull
    private String surname;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "experience", nullable = false)
    @NonNull
    private int experience;

    @OneToOne(mappedBy = "trainer")
    private TrainerSchedule trainerSchedule;

    @OneToMany(mappedBy = "trainer")
    private List<Training> trainings;

}

