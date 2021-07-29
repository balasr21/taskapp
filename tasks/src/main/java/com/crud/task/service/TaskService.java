package com.crud.task.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.crud.task.model.Task;

@Service
public interface TaskService {

    public Task createTask(Task userRequest);

    public Task getTask(UUID userId);

    public Task updateTask(Task user);

    public void deleteTask(UUID userId);

}
