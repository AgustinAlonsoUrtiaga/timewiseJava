package com.timewise.timewise.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean urgent = false;

    private Integer estimatedTime;
    private String timeUnit;
    private String status;

    @Column(nullable = false)
    private Integer timeUsed = 0;

    private String scrumSection;

    @Column(nullable = false)
    private Integer priority = 3;

    @Column(nullable = false)
    private LocalDate createdDate = LocalDate.now();

    private LocalDate dueDate;

    private String environment;

}