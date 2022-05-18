package com.niit.notification.service;

import com.niit.notification.model.Notification;
import com.niit.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService{

    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification, int notificationId) {
        if (notificationRepository.findById(notificationId).isEmpty()){
            return null;
        }
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications(int notificationId) {
        return notificationRepository.findByNotificationId(notificationId);
    }
}
