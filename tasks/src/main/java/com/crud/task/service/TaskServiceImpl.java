package com.crud.task.service;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.crud.task.exception.TaskNotFoundException;
import com.crud.task.model.Task;
import com.crud.task.repository.TaskRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    /**
     * Creates task for the given details, status and Id if passed in the request will be ignored
     *
     * @param taskRequest
     * @return
     */
    @Override
    public Task createTask(Task taskRequest) {
        taskRequest.setStatus(Task.Status.ACTIVE);
        taskRequest.setId(UUID.randomUUID());
        taskRequest.setCreationTime(OffsetDateTime.now());
        taskRequest.setUpdatedTime(null);
        return taskRepository.save(taskRequest);
    }

    /**
     * Returns the task details for the given taskId
     *
     * @param taskId
     * @return
     */
    @Override
    public Task getTask(UUID taskId) {
        log.info("Identifying task with Id [{}]", taskId);
        return taskRepository.findById(taskId).get();
    }

    /**
     * Performs update for the given taskId if found, throws an exception if not found
     *
     * @param task
     * @return
     */
    @Override
    public Task updateTask(Task task) {
        Task existingTask = getTask(task.getId());
        if (existingTask == null) {
            log.warn("Task not found with Id [{}], update unsuccessful", task.getId());
            throw new TaskNotFoundException("Task with Id not found," + task.getId());
        }
        task.setUpdatedTime(OffsetDateTime.now());
        return taskRepository.save(task);
    }

    /**
     * Performs a soft delete of task if found, throws an exception if not found
     *
     * @param taskId
     */
    @Override
    public void deleteTask(UUID taskId) {
        Task inActiveTask = getTask(taskId);
        if (inActiveTask == null) {
            log.warn("Task not found with Id [{}], Delete unsuccessful", taskId);
            throw new TaskNotFoundException("Task with Id not found," + taskId);
        }
        inActiveTask.setStatus(Task.Status.INACTIVE);
        inActiveTask.setUpdatedTime(OffsetDateTime.now());
        updateTask(inActiveTask);
    }

}
