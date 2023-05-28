package com.iaroslaveremeev.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

/**
 * The schedule of the trainer (coach),
 * shows what days of the week at what time the trainer conducts classes
 */
@Entity
@Table(name = "trainer_schedules")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class TrainerSchedule {
    @OneToOne
    @JoinColumn(name = "id_trainer", referencedColumnName = "id")
    @Id
    private Trainer trainer;

    @Column(name = "monday_start")
    private LocalTime mondayStart;

    @Column(name = "monday_end")
    private LocalTime mondayEnd;

    @Column(name = "tuesday_start")
    private LocalTime tuesdayStart;

    @Column(name = "tuesday_end")
    private LocalTime tuesdayEnd;

    @Column(name = "wednesday_start")
    private LocalTime wednesdayStart;

    @Column(name = "wednesday_end")
    private LocalTime wednesdayEnd;

    @Column(name = "thursday_start")
    private LocalTime thursdayStart;

    @Column(name = "thursday_end")
    private LocalTime thursdayEnd;

    @Column(name = "friday_start")
    private LocalTime fridayStart;

    @Column(name = "friday_end")
    private LocalTime fridayEnd;

    @Column(name = "saturday_start")
    private LocalTime saturdayStart;

    @Column(name = "saturday_end")
    private LocalTime saturdayEnd;

    @Column(name = "sunday_start")
    private LocalTime sundayStart;

    @Column(name = "sunday_end")
    private LocalTime sundayEnd;

}
