package com.niit.taskService.service;

import com.niit.taskService.exception.UserNotFoundException;
import com.niit.taskService.model.Archive;
import com.niit.taskService.model.Notification;
import com.niit.taskService.model.Task;
import com.niit.taskService.model.User;
import com.niit.taskService.exception.TaskNotFoundException;
import com.niit.taskService.exception.UserAlreadyExistException;

import java.util.List;
import java.util.Optional;

public interface UserTaskService {

    Object saveUser(User user) throws UserAlreadyExistException;

    List<Task> getAllTasks(String username) throws UserNotFoundException;

    boolean deleteUserTaskFromList(String taskId, String username) throws TaskNotFoundException,UserNotFoundException;

    Optional<Task> findByTaskId(String taskId, String username) throws UserNotFoundException,TaskNotFoundException;

    Task saveUserTask(String username, Task task) throws UserNotFoundException;

    Object loginUser(User user) throws UserAlreadyExistException;

    Archive addToArchive(Archive archive,String username);

    Object getArchiveTasks(String email);

    Object deleteArchiveTask(String taskId,String email);

    Notification saveNotification(Notification notification);

    Object updateNotification(Notification notification, int notificationId);

    Object getAllNotifications(int notificationId);

    Object updateTask(Task task, String taskId, String username) throws UserNotFoundException,TaskNotFoundException;

    List<Task> getPendingTasks(String username) throws UserNotFoundException;

    List<Task> getCompletedTasks(String username) throws UserNotFoundException;

    boolean markTaskAsCompleted(String username, String taskId) throws UserNotFoundException, TaskNotFoundException;

}
