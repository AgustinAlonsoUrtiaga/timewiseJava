package com.timewise.timewise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timewise.timewise.model.Task;
import com.timewise.timewise.repository.TaskRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByPriorityAsc();
    }

    public Task updatePriority(Long taskId, Integer newPriority) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        task.setPriority(newPriority);
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public List<Task> getTasksByEnvironment(String environment) {
        return taskRepository.findByEnvironment(environment);
    }

    public Task createTask(Task taskData) {
        return taskRepository.save(taskData);
    }

    public Task updateTask(Long id, Task taskData) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        existingTask.setTitle(taskData.getTitle());
        existingTask.setDescription(taskData.getDescription());
        existingTask.setUrgent(taskData.getUrgent());
        existingTask.setEstimatedTime(taskData.getEstimatedTime());
        existingTask.setTimeUnit(taskData.getTimeUnit());
        existingTask.setStatus(taskData.getStatus());
        existingTask.setTimeUsed(taskData.getTimeUsed());
        existingTask.setScrumSection(taskData.getScrumSection());
        existingTask.setPriority(taskData.getPriority());
        existingTask.setDueDate(taskData.getDueDate());
        existingTask.setEnvironment(taskData.getEnvironment());

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        taskRepository.delete(task);
    }
}