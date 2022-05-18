package com.niit.taskService.config;

import com.niit.rabbitmq.domain.ArchiveDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier("directExchange")
    private DirectExchange exchange;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    public Object sendMessageToRabbitMq(ArchiveDTO archiveDTO)
    {
        Object o=rabbitTemplate.convertSendAndReceive(exchange.getName(),"archive_routing",archiveDTO);
        return o;
    }
}
