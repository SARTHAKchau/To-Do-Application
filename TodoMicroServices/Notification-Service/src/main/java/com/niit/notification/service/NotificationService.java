package com.niit.notification.service;

import com.niit.notification.model.Notification;

import java.util.List;

public interface NotificationService {
    Notification saveNotification(Notification notification);
    Notification updateNotification(Notification notification,int notificationId);
    List<Notification> getAllNotifications(int notificationId);
}
