package com.event.redis;

import com.event.redis.entity.CityInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

@SpringBootTest
class WebRedisEventApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ChannelTopic topic;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void contextLoads() {
    }


    @Test
    void testRedisPubSub() throws JsonProcessingException {
        redisTemplate.convertAndSend(
                topic.getTopic(),

                objectMapper.writeValueAsString(new CityInfo("hefei", 117.17, 31.52))
        );

    }

}
