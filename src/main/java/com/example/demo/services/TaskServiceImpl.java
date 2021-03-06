package com.example.demo.services;

import com.example.demo.entities.Task;
import com.example.demo.entities.User;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void addTask(Task task, User user) {
        task.setUser(user);
        taskRepository.save(task);
    }

    public List<Task> findByUser(User user) {
        return taskRepository.findByUser(user);
    }
}
