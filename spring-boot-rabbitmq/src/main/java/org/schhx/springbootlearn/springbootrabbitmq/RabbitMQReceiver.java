package org.schhx.springbootlearn.springbootrabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author shanchao
 * @date 2019-03-20
 */
@Component
@RabbitListener(queues = "hello")
@Slf4j
public class RabbitMQReceiver {

    @RabbitHandler
    public void process(String context) {
        log.info("receive: {}", context);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
//        throw new RuntimeException();
    }
}
