package top.zoowayss.rabbitmq.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: <a href="https://github.com/zoowayss">zoowayss</a>
 * @Date: 2024/3/16 11:36
 */
@Slf4j
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.74");
        factory.setPort(5672);
        factory.setUsername("user");
        factory.setPassword("password");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = String.join(" ", argv);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            log.info(" [x] Sent '{}'", message);
        }
    }
}