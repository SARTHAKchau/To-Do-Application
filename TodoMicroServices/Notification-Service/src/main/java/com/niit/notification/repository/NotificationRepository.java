package com.niit.notification.repository;

import com.niit.notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification,Integer> {
    List<Notification> findById(int notificationId);

    List<Notification> findByNotificationId(int notificationId);
}
