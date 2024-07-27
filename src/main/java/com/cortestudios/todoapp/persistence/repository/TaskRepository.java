package com.cortestudios.todoapp.persistence.repository;

import com.cortestudios.todoapp.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {}
