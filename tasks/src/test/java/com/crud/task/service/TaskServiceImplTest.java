package com.crud.task.service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.crud.task.model.Task;
import com.crud.task.repository.TaskRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {


    }

    private Task getTask() {
        return Task.builder().id(UUID.randomUUID()).status(Task.Status.ACTIVE).summary("TestSummary").details("TestDetails").targetTime(OffsetDateTime.now()).creationTime(OffsetDateTime.now())
                   .build();
    }

    @Test
    void testCreateTask() {
        when(taskRepository.save(any())).thenReturn(getTask());
        Task taskRequest = Task.builder().summary("TestSummary").details("TestDetails").targetTime(OffsetDateTime.now()).build();
        Task createdTask = taskService.createTask(taskRequest);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdTask.getId()),
                () -> Assertions.assertEquals(Task.Status.ACTIVE, createdTask.getStatus()),
                () -> Assertions.assertNotNull(createdTask.getCreationTime()),
                () -> Assertions.assertNotNull(createdTask.getTargetTime()),
                () -> Assertions.assertEquals("TestDetails", createdTask.getDetails()),
                () -> Assertions.assertEquals("TestSummary", createdTask.getSummary())
        );
    }

    @Test
    void testGetTask() {
        when(taskRepository.findById(any())).thenReturn(Optional.of(getTask()));
        Task taskRequest = Task.builder().summary("TestSummary").details("TestDetails").targetTime(OffsetDateTime.now()).build();
        Task getTask = taskService.getTask(UUID.randomUUID());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(getTask.getId()),
                () -> Assertions.assertEquals(Task.Status.ACTIVE, getTask.getStatus()),
                () -> Assertions.assertNotNull(getTask.getCreationTime()),
                () -> Assertions.assertNotNull(getTask.getTargetTime()),
                () -> Assertions.assertEquals("TestDetails", getTask.getDetails()),
                () -> Assertions.assertEquals("TestSummary", getTask.getSummary())
        );
    }

    @Test
    void testUpdateTask() {
        when(taskRepository.findById(any())).thenReturn(Optional.of(getTask()));
        when(taskRepository.save(any())).thenReturn(getTask());
        Task updateTask = taskService.updateTask(getTask());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(updateTask.getId()),
                () -> Assertions.assertEquals(Task.Status.ACTIVE, updateTask.getStatus()),
                () -> Assertions.assertNotNull(updateTask.getCreationTime()),
                () -> Assertions.assertNotNull(updateTask.getTargetTime()),
                () -> Assertions.assertEquals("TestDetails", updateTask.getDetails()),
                () -> Assertions.assertEquals("TestSummary", updateTask.getSummary())
        );
    }

    @Test
    void testDeleteTask() {
        when(taskRepository.findById(any())).thenReturn(Optional.of(getTask()));
        doNothing().when(taskRepository).deleteById(any());
        Assertions.assertDoesNotThrow(() -> taskService.deleteTask(UUID.randomUUID()));
    }


}