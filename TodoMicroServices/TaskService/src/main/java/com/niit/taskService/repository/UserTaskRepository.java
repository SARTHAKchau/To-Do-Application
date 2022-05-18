package com.niit.taskService.repository;

import com.niit.taskService.model.Task;
import com.niit.taskService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserTaskRepository extends MongoRepository<Task,String> {
    List<Task> findByUsername(String username);

    Task findByUsernameAndTaskId(String username, String taskId);
}
