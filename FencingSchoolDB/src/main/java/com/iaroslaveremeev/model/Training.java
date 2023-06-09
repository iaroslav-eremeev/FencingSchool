package com.iaroslaveremeev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Fencing school training
 * It has the number of the hall, the date and time of the training,
 * as well as the trainer and the apprentice fields.
 * When adding a new training session for a trainer,
 * it must be taken into account that the trainer cannot have
 * more than 3 students at the same time, and also
 * does not have trainings beyond working hours.
 * Only 10 apprentices can train in the hall at the same time.
 * A student cannot attend several trainings at once on the same day.
 */
@Entity
@Table(name = "trainings")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "number_gym", nullable = false)
    private int numberGym;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "apprentice_id", nullable = false)
    private Apprentice apprentice;

    @Column(name = "training_date", nullable = false)
    @NonNull
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    @NonNull
    private LocalTime timeStart;
}
