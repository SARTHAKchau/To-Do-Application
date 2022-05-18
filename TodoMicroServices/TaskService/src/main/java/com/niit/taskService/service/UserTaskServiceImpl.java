package com.niit.taskService.service;

import com.niit.rabbitmq.domain.ArchiveDTO;
import com.niit.rabbitmq.domain.NotificationDTO;
import com.niit.taskService.config.*;
import com.niit.taskService.exception.UserNotFoundException;
import com.niit.taskService.model.Archive;
import com.niit.taskService.model.Notification;
import com.niit.taskService.model.Task;
import com.niit.taskService.model.User;
import com.niit.taskService.proxy.UserProxy;
import com.niit.taskService.repository.UserTaskRepository;
import com.niit.taskService.exception.TaskNotFoundException;
import com.niit.taskService.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserTaskServiceImpl implements UserTaskService {

    private UserTaskRepository userTaskRepository;
    private UserProxy userProxy;

    @Autowired
    Producer producer;

    @Autowired
    GetAllArchiveClient getAllArchiveClient;

    @Autowired
    DeleteArchiveTaskClient deleteArchiveTaskClient;

    @Autowired
    SaveNotificationClient saveNotificationClient;

    @Autowired
    UpdateNotificationClient updateNotificationClient;

    @Autowired
    GetAllNotificationClient getAllNotificationClient;

    @Autowired
    public UserTaskServiceImpl(UserTaskRepository userTaskRepository, UserProxy userProxy) {
        this.userTaskRepository = userTaskRepository;
        this.userProxy=userProxy;
    }

    @Override
    public Object saveUser(User user) throws UserAlreadyExistException {

        Object o= userProxy.saveUser(user);
        System.out.println(o);
        return o;
    }

    @Override
    public List<Task> getAllTasks(String username) throws UserNotFoundException {
        if(userTaskRepository.findByUsername(username).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return userTaskRepository.findByUsername(username);
    }

    @Override
    public boolean deleteUserTaskFromList(String taskId, String username) throws TaskNotFoundException, UserNotFoundException {
        boolean taskIdIsPresent = false;
        if(userTaskRepository.findByUsername(username).isEmpty()) {
            throw new UserNotFoundException();
        }
        userTaskRepository.deleteById(taskId);
        System.out.println("hi");
        return true;
    }

    @Override
    public Optional<Task> findByTaskId(String taskId, String username)throws UserNotFoundException,TaskNotFoundException {
        if(userTaskRepository.findByUsername(username).isEmpty()) {
            throw new UserNotFoundException();
        }
        return userTaskRepository.findById(taskId);
    }

    @Override
    public Task updateTask(Task task, String taskId, String username) throws UserNotFoundException,TaskNotFoundException {
        if(userTaskRepository.findByUsername(username).isEmpty())
        {
            throw new UserNotFoundException();
        }
        task.setUsername(username);
        return userTaskRepository.save(task);
    }


    @Override
    public Task saveUserTask(String username, Task task) throws UserNotFoundException {
        task.setUsername(username);
        return userTaskRepository.save(task);
    }

    @Override
    public Object loginUser(User user) throws UserAlreadyExistException {
        Object o=userProxy.loginUser(user);
        return o;
    }

    @Override
    public Archive addToArchive(Archive archive,String username) {
        ArchiveDTO archiveDTO=new ArchiveDTO();
        archiveDTO.setTaskId(archive.getTaskId());
        archiveDTO.setTitle(archive.getTitle());
        archiveDTO.setUsername(username);
        archiveDTO.setCategory(archive.getCategory());
        archiveDTO.setDescription(archive.getDescription());
        archiveDTO.setStartDate(archive.getStartDate());
        archiveDTO.setPriority(archive.getPriority());
        archiveDTO.setEndDate(archive.getEndDate());
        producer.sendMessageToRabbitMq(archiveDTO);
        return archive;
    }

    @Override
    public Object getArchiveTasks(String username) {
        return getAllArchiveClient.getAllArchiveTasks(username);
    }

    @Override
    public Object deleteArchiveTask(String taskId,String username){
        ArchiveDTO archiveDTO=new ArchiveDTO();
        archiveDTO.setTaskId(taskId);
        archiveDTO.setUsername(username);
        return deleteArchiveTaskClient.deleteTask(archiveDTO);
    }

    @Override
    public Notification saveNotification(Notification notification) {
        NotificationDTO notificationDTO=new NotificationDTO();
        notificationDTO.setNotificationId(notification.getNotificationId());
        notificationDTO.setMessage(notification.getMessage());
//        notificationDTO.setEmail(notification.getEmail());
        saveNotificationClient.saveNotificationtoRabbitmq(notificationDTO);
        return notification;
    }

    @Override
    public Object updateNotification(Notification notification, int notificationId) {
        System.out.println(notificationId);
        NotificationDTO notificationDTO=new NotificationDTO();
        notificationDTO.setNotificationId(notificationId);
        notificationDTO.setMessage(notification.getMessage());
//        notificationDTO.setEmail(notification.getEmail());
        Object o=updateNotificationClient.updateNotificationtoRabbitmq(notificationDTO);
        return o;
    }

    @Override
    public Object getAllNotifications(int notificationId) {
        return getAllNotificationClient.getAllNotification(notificationId);
    }
    @Override
    public List<Task> getPendingTasks(String username) throws UserNotFoundException {
        List<Task> tasks = userTaskRepository.findByUsername(username);
        if (tasks.isEmpty()) {
            throw new UserNotFoundException();
        }
        List<Task> pendingTasks = tasks.stream().filter(t -> !t.isCompleted()).collect(Collectors.toList());
        return pendingTasks;

    }

    @Override
    public List<Task> getCompletedTasks(String username) throws UserNotFoundException {
        List<Task> tasks = userTaskRepository.findByUsername(username);
        if (tasks.isEmpty()) {
            throw new UserNotFoundException();
        }
        List<Task> completedTasks = tasks.stream().filter(t -> t.isCompleted()).collect(Collectors.toList());
        return completedTasks;
    }

    @Override
    public boolean markTaskAsCompleted(String username, String taskId) throws UserNotFoundException, TaskNotFoundException {
        Task task = userTaskRepository.findByUsernameAndTaskId(username,taskId);
        if (task==null) {
            throw new UserNotFoundException();
        }
        task.setCompleted(true);
        userTaskRepository.save(task);
        return task.isCompleted();
    }






}
