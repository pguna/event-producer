package com.study.event_producer.config;

import com.ibm.mq.jakarta.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import jakarta.jms.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MQConfig {

    @Value("${ibm.mq.queue-manager}")
    private String qm;

    @Value("${ibm.mq.channel}")
    private String channel;

    @Value("${ibm.mq.conn-name}")
    private String connName;

    @Value("${ibm.mq.user}")
    private String user;

    @Value("${ibm.mq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        final MQConnectionFactory mqConnectionFactory = new MQConnectionFactory();
        try {
            mqConnectionFactory.setQueueManager(qm);
            mqConnectionFactory.setChannel(channel);
            mqConnectionFactory.setConnectionNameList(connName);
            mqConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            mqConnectionFactory.setStringProperty(WMQConstants.USERID, user);
            mqConnectionFactory.setStringProperty(WMQConstants.PASSWORD, password);
            mqConnectionFactory.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
        } catch (Exception e) {
            throw new RuntimeException("Error when configuring MQ Connection Factory");
        }
        return mqConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(connectionFactory());
    }

}
