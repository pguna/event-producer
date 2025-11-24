package com.study.event_producer.service;

import com.study.event_producer.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class MQService {

    @Value("${mq.queue.request}")
    private String requestQueue;

    private final JmsTemplate jmsTemplate;

    public Message sendMessage(final Message message) {
        final Date currentDate = new Date();
        final String messageContent = "Hello from Guna at " + currentDate;
        message.setCreatedAt(currentDate);
        message.setMessage(messageContent);
        jmsTemplate.convertAndSend(requestQueue, message);
        return message;
    }

    @JmsListener(destination = "${mq.queue.request}")
    public void receiveMessage(final Message message) {
      log.info("[Request Queue] Received Message: {}", message);
    }

}
