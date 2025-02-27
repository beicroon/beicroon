package com.beicroon.provider.rocketmq.producer;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.json.template.JsonTemplate;
import com.beicroon.construct.mq.enums.MqDelayEnums;
import com.beicroon.construct.mq.producer.IMqProducer;
import com.beicroon.provider.rocketmq.callback.RocketMqSendCallback;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Import({RocketMqSendCallback.class})
public class RocketMqProducer implements IMqProducer {

    @Value("${rocketmq.timeout:3000}")
    private Long timeout;

    @Resource
    private JsonTemplate jsonTemplate;

    @Lazy
    @Resource
    private DefaultMQProducer producer;

    @Resource
    private RocketMqSendCallback sendCallback;

    @Override
    public void send(String topic, String tag, String key, Object content, MqDelayEnums delay) {
        Message message = new Message(topic, tag, key, this.jsonTemplate.toJson(content).getBytes(SystemConstant.CHARSET));

        if (delay != null) {
            message.setDelayTimeLevel(delay.getCode());
        }

        try {
            this.producer.send(message, this.sendCallback, this.timeout);
        } catch (RemotingException | InterruptedException | MQClientException ex) {
            throw new RuntimeException(ex);
        }
    }

}
