package com.iaroslaveremeev.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * System user
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login", nullable = false, unique = true)
    @NonNull
    private String login;

    @Column(name = "password", nullable = false)
    @NonNull
    private char[] password;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "registration_date")
    @NonNull
    private LocalDate regDate = LocalDate.now();

}