package org.schhx.springbootlearn.springbootrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootRabbitmqApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootRabbitmqApplication.class, args);

        RabbitMQSender sender = context.getBean(RabbitMQSender.class);
        for (int i = 0; i < 10; i++) {
            sender.send(String.valueOf(i));
        }
    }

}
