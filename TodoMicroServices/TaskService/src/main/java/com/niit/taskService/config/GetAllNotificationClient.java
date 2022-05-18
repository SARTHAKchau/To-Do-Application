package com.niit.taskService.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GetAllNotificationClient {
    private RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier("getAllNotification")
    private DirectExchange exchange;

    @Autowired
    public GetAllNotificationClient(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    public Object getAllNotification(int notificationId)
    {
        Object o=rabbitTemplate.convertSendAndReceive(exchange.getName(),"get_notification_routing",notificationId);
        return o;
    }
}
