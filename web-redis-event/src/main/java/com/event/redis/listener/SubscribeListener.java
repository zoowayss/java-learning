package com.event.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * <h1>消息监听器</h1>
 */
@Slf4j
public class SubscribeListener implements MessageListener {

    /**
     * <h2>消息回调</h2>
     * @param message {@link Message} 消息体 + ChannelName
     * @param pattern 订阅的 pattern, ChannelName 的模式匹配
     * */
    @Override
    public void onMessage(Message message, byte[] pattern) {

        String body = new String(message.getBody());
        String channel = new String(message.getChannel());
        String pattern_ = new String(pattern);

        log.info("body: {}, channel: {}, pattern: {}", body, channel, pattern_);
    }
}