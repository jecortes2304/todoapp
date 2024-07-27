package com.cortestudios.todoapp.service;

import com.cortestudios.todoapp.dto.TaskCreateDTO;
import com.cortestudios.todoapp.dto.TaskStatusDTO;
import com.cortestudios.todoapp.dto.TaskUpdateDTO;
import com.cortestudios.todoapp.util.mapper.impl.TaskCreateDTOToTask;
import com.cortestudios.todoapp.persistence.entity.Task;
import com.cortestudios.todoapp.persistence.repository.TaskRepository;
import jakarta.el.MethodNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskCreateDTOToTask taskCreateDTOToTask;

    public TaskService(TaskRepository taskRepository, TaskCreateDTOToTask taskCreateDTOToTask) {
        this.taskRepository = taskRepository;
        this.taskCreateDTOToTask = taskCreateDTOToTask;
    }

    public Task createTask(TaskCreateDTO taskCreateDTO) {
        Task task = taskCreateDTOToTask.map(taskCreateDTO);
        return this.taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    @Transactional
    public Task updateTaskByStatusDto(TaskUpdateDTO taskUpdateDTO, TaskStatusDTO taskStatusDTO, Long id) {
        Task task = getTaskById(id);
        Optional.ofNullable(taskUpdateDTO.getTitle()).ifPresent(task::setTitle);
        Optional.ofNullable(taskUpdateDTO.getDescription()).ifPresent(task::setDescription);
        Optional.ofNullable(taskUpdateDTO.getFinished()).ifPresent(task::setFinished);
        task.setTaskStatusDTO(taskStatusDTO);
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() ->  new MethodNotFoundException("Task not found"));
    }

    public List<Task> getAllTasks(Pageable paging) {
        Iterable<Task> tasksIterable =
                taskRepository.findAll(paging);
        List<Task> tasks = new ArrayList<>();
        tasksIterable.forEach(tasks::add);
        return tasks;
    }
}
