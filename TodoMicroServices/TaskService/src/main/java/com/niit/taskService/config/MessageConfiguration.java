package com.niit.taskService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    private String exchangeName="task_exchange";
    private String taskQueue="task_queue";

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(exchangeName);
    }


    @Bean
    public Queue taskQueue()
    {
        return new Queue(taskQueue,true);
    }


    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter()
    {
        return new  Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemp=new RabbitTemplate(connectionFactory);
        rabbitTemp.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemp;
    }

    @Bean
    Binding bindingUser(@Qualifier("taskQueue") Queue taskQueue,@Qualifier("directExchange") DirectExchange exchange)
    {
        return BindingBuilder.bind(taskQueue()).to(exchange).with("archive_routing");
    }

    private String getExchangeName="get_archive_exchange";
    private String getArchiveQueue="get_archive_queue";

    @Bean
    public DirectExchange getAllArchive()
    {
        return new DirectExchange(getExchangeName);
    }

    @Bean
    public Queue getArchiveQueue()
    {
        return new Queue(getArchiveQueue,true);
    }

    @Bean
    Binding getAllBinding(@Qualifier("getArchiveQueue")Queue getArchiveQueue, @Qualifier("getAllArchive") DirectExchange exchange)
    {
        return BindingBuilder.bind(getArchiveQueue()).to(exchange).with("get_archive_routing");
    }

    private String delExchangeName="del_archive_exchange";
    private String delArchiveQueue="del_archive_queue";

    @Bean
    public DirectExchange delArchive()
    {
        return new DirectExchange(delExchangeName);
    }

    @Bean
    public Queue delArchiveQueue()
    {
        return new Queue(delArchiveQueue,true);
    }

    @Bean
    Binding delBinding(@Qualifier("delArchiveQueue")Queue delArchiveQueue, @Qualifier("delArchive") DirectExchange exchange)
    {
        return BindingBuilder.bind(delArchiveQueue()).to(exchange).with("del_archive_routing");
    }

    private String saveNotificationExchangeName="save_notification_exchange";
    private String saveNotificationQueue="notification_queue";

    @Bean
    public DirectExchange saveNotification()
    {
        return new DirectExchange(saveNotificationExchangeName);
    }

    @Bean
    public Queue saveNotificationQueue()
    {
        return new Queue(saveNotificationQueue,true);
    }

    @Bean
    Binding saveNotificationBinding(@Qualifier("saveNotificationQueue")Queue saveNotificationQueue, @Qualifier("saveNotification") DirectExchange exchange)
    {
        return BindingBuilder.bind(saveNotificationQueue()).to(exchange).with("notification_routing");
    }

    private String updateNotificationExchangeName="update_notification_exchange";
    private String updateNotificationQueue="update_notification_queue";

    @Bean
    public DirectExchange updateNotification()
    {
        return new DirectExchange(updateNotificationExchangeName);
    }

    @Bean
    public Queue updateNotificationQueue()
    {
        return new Queue(updateNotificationQueue,true);
    }

    @Bean
    Binding updateNotificationBinding(@Qualifier("updateNotificationQueue")Queue updateNotificationQueue, @Qualifier("updateNotification") DirectExchange exchange)
    {
        return BindingBuilder.bind(updateNotificationQueue()).to(exchange).with("update_notification_routing");
    }

    private String getNotificationExchangeName="get_notification_exchange";
    private String getNotificationQueue="get_notification_queue";

    @Bean
    public DirectExchange getAllNotification()
    {
        return new DirectExchange(getNotificationExchangeName);
    }

    @Bean
    public Queue getNotificationQueue()
    {
        return new Queue(getNotificationQueue,true);
    }

    @Bean
    Binding getNotificationBinding(@Qualifier("getNotificationQueue")Queue getNotificationQueue, @Qualifier("getAllNotification") DirectExchange exchange)
    {
        return BindingBuilder.bind(getNotificationQueue()).to(exchange).with("get_notification_routing");
    }
}
