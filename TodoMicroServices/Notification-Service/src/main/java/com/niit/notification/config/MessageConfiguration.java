package com.niit.notification.config;

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
    static final String NotificationExchangeName = "save_notification_exchange";
    static final String NotificationQueueName = "notification_queue";
    public static final String NotificationRoutingKey = "notification_routing";

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    Queue queue() {
        return new Queue(NotificationQueueName);
    }

    @Bean
    DirectExchange notificationExchange() {
        return new DirectExchange(NotificationExchangeName);
    }

    @Bean
    Binding taskBinding(@Qualifier("queue") Queue queue, @Qualifier("notificationExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue()).to(exchange).with(NotificationRoutingKey);
    }

    static final String updateNotificationExchangeName = "update_notification_exchange";
    static final String updateNotificationQueueName = "update_notification_queue";
    public static final String updateNotificationRoutingKey = "update_notification_routing";


    @Bean
    Queue updateNotificationQueue() {
        return new Queue(updateNotificationQueueName);
    }

    @Bean
    DirectExchange updateNotificationExchange() {
        return new DirectExchange(updateNotificationExchangeName);
    }

    @Bean
    Binding updateNotificationBinding(@Qualifier("updateNotificationQueue") Queue updateNotificationQueue,@Qualifier("updateNotificationExchange") DirectExchange exchange) {
        return BindingBuilder.bind(updateNotificationQueue()).to(exchange).with(updateNotificationRoutingKey);
    }

    static final String getNotificationExchangeName = "get_notification_exchange";
    static final String getNotificationQueueName = "get_notification_queue";
    public static final String getNotificationRoutingKey = "get_notification_routing";


    @Bean
    Queue getNotificationQueue() {
        return new Queue(getNotificationQueueName);
    }

    @Bean
    DirectExchange getNotificationExchange() {
        return new DirectExchange(getNotificationExchangeName);
    }

    @Bean
    Binding getNotificationBinding(@Qualifier("getNotificationQueue") Queue getNotificationQueue,@Qualifier("getNotificationExchange") DirectExchange exchange) {
        return BindingBuilder.bind(getNotificationQueue()).to(exchange).with(getNotificationRoutingKey);
    }
}
