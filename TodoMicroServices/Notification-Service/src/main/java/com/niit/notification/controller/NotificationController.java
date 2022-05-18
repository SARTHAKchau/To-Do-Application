package com.niit.notification.controller;

import com.niit.notification.model.Notification;
import com.niit.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/notification/")
public class NotificationController {
    private ResponseEntity responseEntity;
    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("saveNotification")
    public ResponseEntity<?> saveNotification(@RequestBody Notification notification){
        notificationService.saveNotification(notification);
        responseEntity = new ResponseEntity(notification, HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping("notification/{notificationId}")
    public ResponseEntity<?> updateNotification(@RequestBody Notification notification,@PathVariable("notificationId") int notificationId){
        return new ResponseEntity<>(notificationService.updateNotification(notification,notificationId),HttpStatus.OK);
    }

    @GetMapping("notifications/{notificationId}")
    public ResponseEntity<?> getAllNotification(@PathVariable("notificationId")int notificationId){
        try{
            responseEntity = new ResponseEntity(notificationService.getAllNotifications(notificationId),HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity("Error...",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
