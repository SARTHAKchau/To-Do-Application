package com.niit.taskService.config;

import com.niit.rabbitmq.domain.ArchiveDTO;
import com.niit.rabbitmq.domain.NotificationDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SaveNotificationClient {
    private RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier("saveNotification")
    private DirectExchange exchange;

    @Autowired
    public SaveNotificationClient(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    public Object saveNotificationtoRabbitmq(NotificationDTO notificationDTO)
    {
        Object o=rabbitTemplate.convertSendAndReceive(exchange.getName(),"notification_routing",notificationDTO);
        return o;
    }
}
