package com.beicroon.construct.rocketmq.producer;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.jackson.utils.JsonUtils;
import com.beicroon.construct.mq.enums.MqDelayEnums;
import com.beicroon.construct.mq.producer.IMqProducer;
import com.beicroon.construct.rocketmq.callback.RocketMqSendCallback;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

@Slf4j
@Import({RocketMqSendCallback.class, DefaultMQProducer.class})
public class RocketMqProducer implements IMqProducer {

    @Value("${mq.producer.timeout:3000}")
    private Long timeout;

    @Resource
    private DefaultMQProducer producer;

    @Resource
    private RocketMqSendCallback sendCallback;

    @Override
    public void send(String topic, String tag, String key, Object content, MqDelayEnums delay) {
        Message message = new Message(topic, tag, key, JsonUtils.toJson(content).getBytes(SystemConstant.CHARSET));

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
