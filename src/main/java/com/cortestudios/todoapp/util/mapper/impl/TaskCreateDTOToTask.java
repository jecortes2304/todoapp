package com.cortestudios.todoapp.util.mapper.impl;

import com.cortestudios.todoapp.dto.TaskCreateDTO;
import com.cortestudios.todoapp.dto.TaskStatusDTO;
import com.cortestudios.todoapp.persistence.entity.Task;
import com.cortestudios.todoapp.util.mapper.IMapper;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;


@Component
public class TaskCreateDTOToTask implements IMapper<TaskCreateDTO, Task> {
    @Override
    public Task map(TaskCreateDTO in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEndedDate(in.getEndedDate());
        task.setCreateDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatusDTO(TaskStatusDTO.ON_TIME);
        return task;
    }
}
