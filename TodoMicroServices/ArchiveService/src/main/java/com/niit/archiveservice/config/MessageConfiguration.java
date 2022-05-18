package com.niit.archiveservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    static final String taskExchangeName = "task_exchange";
    static final String taskQueueName = "archive_task_queue";
    public static final String taskRoutingKey = "archive_routing";

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    Queue queue() {
        return new Queue(taskQueueName);
    }

    @Bean
    DirectExchange taskExchange() {
        return new DirectExchange(taskExchangeName);
    }

    @Bean
    Binding taskBinding(@Qualifier("queue") Queue queue, @Qualifier("taskExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue()).to(exchange).with(taskRoutingKey);
    }

    static final String getExchangeName = "get_archive_exchange";
    static final String getQueueName = "get_archive_tasks";
    public static final String getFavRoutingKey = "get_archive_routing";


    @Bean
    Queue getQueue() {
        return new Queue(getQueueName);
    }

    @Bean
    DirectExchange getExchange() {
        return new DirectExchange(getExchangeName);
    }

    @Bean
    Binding getFavBinding(@Qualifier("getQueue") Queue queue,@Qualifier("getExchange") DirectExchange exchange) {
        return BindingBuilder.bind(getQueue()).to(exchange).with(getFavRoutingKey);
    }

    static final String delExchangeName = "del_archive_exchange";
    static final String delQueueName = "del_archive_queue";
    public static final String delFavRoutingKey = "del_archive_routing";

    @Bean
    Queue delQueue() {
        return new Queue(delQueueName);
    }

    @Bean
    DirectExchange delExchange() {
        return new DirectExchange(delExchangeName);
    }

    @Bean
    Binding delBinding(@Qualifier("delQueue") Queue delQueue,@Qualifier("delExchange") DirectExchange exchange) {
        return BindingBuilder.bind(delQueue()).to(exchange).with(delFavRoutingKey);
    }
}
