package com.timewise.timewise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timewise.timewise.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEnvironment(String environment);
    List<Task> findAllByOrderByPriorityAsc();

}