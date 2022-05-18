package com.niit.taskService.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.niit.taskService.exception.TaskAlreadyExistsException;
import com.niit.taskService.exception.TaskNotFoundException;
import com.niit.taskService.exception.UserAlreadyExistException;
import com.niit.taskService.exception.UserNotFoundException;
import com.niit.taskService.model.Archive;
import com.niit.taskService.model.Notification;
import com.niit.taskService.model.Task;
import com.niit.taskService.model.User;
import com.niit.taskService.service.SecurityTokenGenerator;
import com.niit.taskService.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "*")
public class TaskController {
    private ResponseEntity<?> responseEntity;
    private UserTaskService userTaskService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public TaskController(UserTaskService userTaskService,SecurityTokenGenerator securityTokenGenerator) {
        this.userTaskService = userTaskService;
        this.securityTokenGenerator=securityTokenGenerator;
    }

    @HystrixCommand(fallbackMethod = "fallbackSaveTask")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    @PostMapping("tasks/{username}")
    public ResponseEntity<?> saveUserTask(@RequestBody Task task,@PathVariable("username") String username) throws UserNotFoundException {
        try {
            System.out.println(task);
            responseEntity = new ResponseEntity<>(userTaskService.saveUserTask(username,task), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @HystrixCommand(fallbackMethod = "fallbackGetTask")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    @GetMapping("tasks/{username}")
    public ResponseEntity<?> getAllTasks(@PathVariable("username") String username) throws UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(userTaskService.getAllTasks(username), HttpStatus.OK);
        }catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @HystrixCommand(fallbackMethod = "fallbackDelTask")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    @DeleteMapping("tasks/{username}/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("username") String username,@PathVariable("taskId")String taskId) throws TaskNotFoundException{
        try {
            responseEntity = new ResponseEntity<>(userTaskService.deleteUserTaskFromList(taskId,username), HttpStatus.OK);
        } catch (UserNotFoundException | TaskNotFoundException m) {
            throw new TaskNotFoundException();
        }
        return responseEntity;
    }

    @HystrixCommand(fallbackMethod = "fallbackUpdateTask")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    @PutMapping("task/{username}/{taskId}")
    public ResponseEntity<?> updateTask(@RequestBody Task task,@PathVariable("taskId") String taskId,@PathVariable("username") String username) throws UserNotFoundException, TaskNotFoundException {
        return new ResponseEntity<>(userTaskService.updateTask(task,taskId,username),HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "fallbackGetByTaskId")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    @GetMapping("task/{username}/{taskId}")
    public ResponseEntity<?> findByTaskId(@PathVariable("taskId")String taskId,@PathVariable("username") String username){
        try
        {
            responseEntity = new ResponseEntity(userTaskService.findByTaskId(taskId,username),HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity("Error,,,",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

//    @HystrixCommand(fallbackMethod = "fallbackReg")
//    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistException {
        try {
            responseEntity =  new ResponseEntity<>(userTaskService.saveUser(user), HttpStatus.CREATED);
        }
        catch(UserAlreadyExistException e)
        {
            throw new UserAlreadyExistException();
        }
        return responseEntity;
    }

    @HystrixCommand(fallbackMethod = "fallbackLog")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserAlreadyExistException {
        Object obj=userTaskService.loginUser(user);
        if(obj==null)
        {
            return new ResponseEntity("Invalid Credentials",HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity(securityTokenGenerator.generateToken(user),HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "fallbackArchTask")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    @PostMapping("archiveTask/{username}")
    public ResponseEntity saveTask(@RequestBody Archive archive,@PathVariable("username") String username) throws TaskAlreadyExistsException {
        Archive task=userTaskService.addToArchive(archive,username);
        return new ResponseEntity("Task Added",HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "fallbackGetArchTask")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    @GetMapping("archive/{username}")
    public ResponseEntity taskLists(@PathVariable("username") String username)
    {
        return  new ResponseEntity(userTaskService.getArchiveTasks(username), HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "fallbackDelArchTask")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    @DeleteMapping("archive/{username}/{taskId}")
    public ResponseEntity deleteArchTask(@PathVariable("taskId")String taskId,@PathVariable("username") String username) {
            userTaskService.deleteArchiveTask(taskId,username);
            responseEntity = new ResponseEntity("Successfully deleted !!!", HttpStatus.OK);
        return responseEntity;
    }

    @HystrixCommand(fallbackMethod = "fallbackSaveNotification")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    @PostMapping("saveNotification")
    public ResponseEntity<?> saveNotification(@RequestBody Notification notification){
        responseEntity = new ResponseEntity(userTaskService.saveNotification(notification), HttpStatus.CREATED);
        return responseEntity;
    }

    @HystrixCommand(fallbackMethod = "fallbackUpdateNotification")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    @PutMapping("notification/{notificationId}")
    public ResponseEntity<?> updateNotification(@RequestBody Notification notification,@PathVariable("notificationId") int notificationId){
        return new ResponseEntity<>(userTaskService.updateNotification(notification,notificationId),HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "fallbackGetNotification")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    @GetMapping("notifications/{notificationId}")
    public ResponseEntity<?> getAllNotification(@PathVariable("notificationId") int notificationId){
        responseEntity = new ResponseEntity(userTaskService.getAllNotifications(notificationId),HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("/pending/{username}")
    public  ResponseEntity<?> getPendingTasks(@PathVariable("username") String username) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(userTaskService.getPendingTasks(username), HttpStatus.OK);
        } catch(UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/completed/{username}")
    public ResponseEntity<?> getCompletedTasks(@PathVariable("username") String username) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(userTaskService.getCompletedTasks(username), HttpStatus.OK);
        } catch(UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("mark-complete/{username}/{taskId}")
    public ResponseEntity<?> markTaskAsComplete(@PathVariable("username") String username, @PathVariable("taskId") String taskId) throws UserNotFoundException, TaskNotFoundException {
        try {
            return new ResponseEntity<>(userTaskService.markTaskAsCompleted(username, taskId), HttpStatus.OK);
        } catch(UserNotFoundException e) {
            throw e;
        } catch(TaskNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> fallbackSaveTask(Task task,String username) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> fallbackGetTask(String username) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> fallbackDelTask(String username,String taskId) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> fallbackUpdateTask(Task task,String taskId,String username) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> fallbackGetByTaskId(String taskId,String username) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> fallbackLog(User user) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> fallbackReg(User user) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> fallbackArchTask(Archive archive,String username) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> fallbackGetArchTask(String email) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> fallbackDelArchTask(String taskId,String email) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> fallbackSaveNotification(Notification notification) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> fallbackUpdateNotification(int notificationId,Notification notification) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> fallbackGetNotification(int notificationId) {
        String msg = "This service is down..Try again later...";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }
}
