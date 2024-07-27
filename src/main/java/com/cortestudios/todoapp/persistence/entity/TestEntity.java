package com.cortestudios.todoapp.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class TestEntity {

    private String name;
    private String description;
}
