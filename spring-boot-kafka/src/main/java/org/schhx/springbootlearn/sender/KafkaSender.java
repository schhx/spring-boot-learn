package org.schhx.springbootlearn.sender;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.schhx.springbootlearn.bean.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @author shanchao
 * @date 2018-06-07
 */
@Component
@Slf4j
@SuppressWarnings("all")
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", JSON.toJSONString(message));
        kafkaTemplate.send("test", JSON.toJSONString(message));
    }
}
