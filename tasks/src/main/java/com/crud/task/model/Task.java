package com.crud.task.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {

    @Id
    private UUID id;
    private String summary;
    private String details;
    private Status status;
    private OffsetDateTime targetTime;
    private OffsetDateTime creationTime;
    private OffsetDateTime updatedTime;


    public enum Status {
        ACTIVE,
        INACTIVE,
        COMPLETED
    }


}
