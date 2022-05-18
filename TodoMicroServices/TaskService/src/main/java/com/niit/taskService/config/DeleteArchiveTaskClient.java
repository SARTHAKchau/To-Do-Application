package com.niit.taskService.config;

import com.niit.rabbitmq.domain.ArchiveDTO;
import com.niit.taskService.exception.TaskNotFoundException;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DeleteArchiveTaskClient {
    private RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier("delArchive")
    private DirectExchange exchange;

    @Autowired
    public DeleteArchiveTaskClient(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    public Object deleteTask(ArchiveDTO archiveDTO)
    {
        Object o=rabbitTemplate.convertSendAndReceive(exchange.getName(),"del_archive_routing",archiveDTO);
        return o;
    }
}
