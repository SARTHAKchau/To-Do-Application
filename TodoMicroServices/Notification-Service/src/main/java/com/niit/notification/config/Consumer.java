package com.niit.notification.config;

import com.niit.notification.model.Notification;
import com.niit.notification.service.NotificationServiceImpl;
import com.niit.rabbitmq.domain.NotificationDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private NotificationServiceImpl notificationService;

    @RabbitListener(queues = "notification_queue")
    public Object saveNotificationDtoFromRabbitMq(NotificationDTO notificationDTO){
        Notification notification=new Notification();
        notification.setNotificationId(notificationDTO.getNotificationId());
        notification.setMessage(notificationDTO.getMessage());
//        notification.setEmail(notificationDTO.getEmail());
        Object o=notificationService.saveNotification(notification);
        return o;
    }

    @RabbitListener(queues = "update_notification_queue")
    public Object updateNotificationDtoFromRabbitMq(NotificationDTO notificationDTO){
        Notification notification=new Notification();
        int notificationId=notificationDTO.getNotificationId();
        System.out.println(notificationId);
        notification.setNotificationId(notificationDTO.getNotificationId());
        notification.setMessage(notificationDTO.getMessage());
//        notification.setEmail(notificationDTO.getEmail());
        Object o=notificationService.updateNotification(notification,notificationId);
        return o;
    }

    @RabbitListener(queues = "get_notification_queue")
    public Object getNotificationDtoFromRabbitMq(int notificationId){
        System.out.println("hi");
        Object o=notificationService.getAllNotifications(notificationId);
        return o;
    }
}
