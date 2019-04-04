package org.schhx.springbootlearn.springbootrabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author shanchao
 * @date 2019-03-20
 */
public class RabbitMQDemo {

    private static final String HOST = "106.15.127.238";
    private static final int PORT = 5672;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";
    private static final String VIRTUAL_HOST = "test";
    private static final String EXCHANGE = "amq.topic";
    private static final String ROUTING_KEY = "hello";
    private static final String QUEUE = "hello";

    public static void main(String[] args) throws Exception {
        publisher();
//        consumer();
    }

    public static void publisher() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUAL_HOST);

        // 创建连接
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        channel.confirmSelect();

        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("handleAck " +  deliveryTag);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {

            }
        });

        String msg = "hello";
        try {
            channel.basicPublish(EXCHANGE, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes("UTF-8"));
            channel.basicPublish(EXCHANGE, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        TimeUnit.SECONDS.sleep(10);

        channel.close();
        connection.close();
    }

    public static void consumer() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUAL_HOST);

        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        // 设置客户端最多接收未被 ack 的消息的个数
        channel.basicQos(64);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                System.out.println(new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE, false, consumer);

        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
}
