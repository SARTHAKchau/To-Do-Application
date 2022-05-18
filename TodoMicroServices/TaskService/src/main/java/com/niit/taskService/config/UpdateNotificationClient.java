package com.niit.taskService.config;

import com.niit.rabbitmq.domain.NotificationDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UpdateNotificationClient {
    private RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier("updateNotification")
    private DirectExchange exchange;

    @Autowired
    public UpdateNotificationClient(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    public Object updateNotificationtoRabbitmq(NotificationDTO notificationDTO)
    {
        Object o=rabbitTemplate.convertSendAndReceive(exchange.getName(),"update_notification_routing",notificationDTO);
        return o;
    }
}
