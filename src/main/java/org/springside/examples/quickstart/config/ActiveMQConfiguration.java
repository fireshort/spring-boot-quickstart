package org.springside.examples.quickstart.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springside.examples.quickstart.jms.NotifyMessageListener;

import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by Ivan on 2015/11/25.
 */
@Configuration
@EnableJms
public class ActiveMQConfiguration {
    @Value("${spring.activemq.broker-url}")
    private String brokerURL;

    @Bean
    public ActiveMQConnectionFactory connectionFactory()
    {
        return new ActiveMQConnectionFactory(brokerURL);
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory()
    {
        CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(connectionFactory());
        cachingConnectionFactory.setSessionCacheSize(10);
        return cachingConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate()
    {
        JmsTemplate jmsTemplate=new JmsTemplate();
        jmsTemplate.setConnectionFactory(cachingConnectionFactory());
        return jmsTemplate;
    }

    @Bean(name = "notifyQueue")
    public Queue queue() {
        return new ActiveMQQueue("q.notify");
    }

    @Bean(name = "notifyTopic")
    public Topic topic() {
        return new ActiveMQTopic("t.notify");
    }

    @Bean
    public DefaultMessageListenerContainer queueContainer() {
        DefaultMessageListenerContainer queueContainer = new DefaultMessageListenerContainer();
        queueContainer.setConnectionFactory(connectionFactory());
        queueContainer.setDestination(queue());
        queueContainer.setMessageListener(notifyMessageListener());
        queueContainer.setConcurrentConsumers(10);
        return queueContainer;
    }

    @Bean
    public DefaultMessageListenerContainer topicContainer() {
        DefaultMessageListenerContainer topicContainer = new DefaultMessageListenerContainer();
        topicContainer.setConnectionFactory(connectionFactory());
        topicContainer.setDestination(topic());
        topicContainer.setMessageListener(notifyMessageListener());
        return topicContainer;
    }

    @Bean
    public MessageListener notifyMessageListener() {
        return new NotifyMessageListener();
    }
}
