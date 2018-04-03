package com.example.demo.services;

import com.example.demo.entities.Task;
import com.example.demo.entities.User;

public interface TaskService {

    public void addTask(Task task, User user);

}
