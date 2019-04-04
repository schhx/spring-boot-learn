package org.schhx.springbootlearn.springbootrabbitmq;

import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shanchao
 * @date 2019-03-22
 */
@Configuration
public class Config {

//    @Bean
//    public DirectMessageListenerContainer directRabbitListenerContainerFactory(DirectRabbitListenerContainerFactory factory){
//        return factory.createListenerContainer();
//    }
}
