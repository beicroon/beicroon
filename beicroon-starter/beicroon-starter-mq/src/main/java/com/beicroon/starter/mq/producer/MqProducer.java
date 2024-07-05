//package com.beicroon.starter.mq.producer;
//
//import com.beicroon.construct.constant.SystemConstant;
//import com.beicroon.construct.jackson.utils.JsonUtils;
//import com.beicroon.starter.mq.callback.MqSendCallback;
//import com.beicroon.starter.mq.enums.MqDelayEnums;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.remoting.exception.RemotingException;
//import org.springframework.beans.factory.NoSuchBeanDefinitionException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Import;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Slf4j
//@Component
//@Import({MqSendCallback.class, DefaultMQProducer.class})
//public class MqProducer {
//
//    @Value("${mq.producer.timeout:3000}")
//    private Long timeout;
//
//    @Resource
//    private DefaultMQProducer producer;
//
//    @Resource
//    private MqSendCallback sendCallback;
//
//    public void send(String topic, String tag, String key, Object content) {
//        this.send(topic, tag, key, content, null);
//    }
//
//    public void send(String topic, String tag, String key, Object content, MqDelayEnums delay) {
//        Message message = new Message(topic, tag, key, JsonUtils.toJson(content).getBytes(SystemConstant.CHARSET));
//
//        if (delay != null) {
//            message.setDelayTimeLevel(delay.getCode());
//        }
//
//        try {
//            this.producer.send(message, this.sendCallback, this.timeout);
//        } catch (RemotingException | InterruptedException | MQClientException ex) {
//            throw new RuntimeException(ex);
//        } catch (NoSuchBeanDefinitionException ex) {
//            throw new RuntimeException("MQ配置错误:请确认MQ服务端地址是否正确配置", ex);
//        }
//    }
//
//}
