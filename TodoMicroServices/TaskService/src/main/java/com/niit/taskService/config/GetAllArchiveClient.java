package com.niit.taskService.config;

import com.niit.rabbitmq.domain.ArchiveDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GetAllArchiveClient {
    private RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier("getAllArchive")
    private DirectExchange exchange;

    @Autowired
    public GetAllArchiveClient(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    public Object getAllArchiveTasks(String email)
    {
        Object o=rabbitTemplate.convertSendAndReceive(exchange.getName(),"get_archive_routing",email);
        return o;
    }
}
