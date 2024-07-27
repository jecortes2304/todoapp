package com.cortestudios.todoapp.persistence.entity;

import com.cortestudios.todoapp.dto.TaskStatusDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createDate;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedDate;
    @Column(nullable = false)
    private LocalDateTime endedDate;
    @Column(nullable = false)
    private boolean finished;
    @Column(nullable = false)
    private TaskStatusDTO taskStatusDTO;
}
