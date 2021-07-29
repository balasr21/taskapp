package com.crud.task.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.task.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

}
