package com.iaroslaveremeev.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * The schedule of the trainer (coach),
 * shows what days of the week at what time the trainer conducts classes
 */
@Entity
@Table(name = "trainer_schedules")
@Data
@NoArgsConstructor
public class TrainerSchedule {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "trainer_id")
    @MapsId
    private Trainer trainer;
    @JsonFormat(pattern="HH:mm")
    @Column(name = "monday_start")
    private LocalTime mondayStart;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "monday_end")
    private LocalTime mondayEnd;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "tuesday_start")
    private LocalTime tuesdayStart;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "tuesday_end")
    private LocalTime tuesdayEnd;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "wednesday_start")
    private LocalTime wednesdayStart;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "wednesday_end")
    private LocalTime wednesdayEnd;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "thursday_start")
    private LocalTime thursdayStart;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "thursday_end")
    private LocalTime thursdayEnd;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "friday_start")
    private LocalTime fridayStart;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "friday_end")
    private LocalTime fridayEnd;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "saturday_start")
    private LocalTime saturdayStart;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "saturday_end")
    private LocalTime saturdayEnd;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "sunday_start")
    private LocalTime sundayStart;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "sunday_end")
    private LocalTime sundayEnd;


    public void add(String dayOfWeek, LocalTime start, LocalTime end) {
        try {
            this.getClass().getDeclaredField(dayOfWeek.toLowerCase() + "_start")
                    .set(this, start);
            this.getClass().getDeclaredField(dayOfWeek.toLowerCase() + "_end")
                    .set(this, end);
        } catch (Exception ignored) {}
    }
}
