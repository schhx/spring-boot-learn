package org.schhx.springbootlearn.springbootrabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author shanchao
 * @date 2019-03-20
 */
@Component
@Slf4j
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String context) {
        log.info("send: {}", "hello " + context);
        this.rabbitTemplate.convertAndSend("amq.topic", "hello", context);
    }
}
