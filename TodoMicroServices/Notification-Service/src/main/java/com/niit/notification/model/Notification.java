package com.niit.notification.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Notification {
    @Id
    private int notificationId;
    private String message;
//    private String email;

    public Notification() {
    }

    public Notification(int notificationId, String message) {
        this.notificationId = notificationId;
        this.message = message;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", message='" + message + '\'' +
                '}';
    }
}
