package com.beicroon.provider.rocketmq.callback;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

@Slf4j
public class RocketMqSendCallback implements SendCallback {

    @Override
    public void onSuccess(SendResult sendResult) {
        log.info("MQ消息推送成功:{}", sendResult);
    }

    @Override
    public void onException(Throwable throwable) {
        log.error("MQ消息推送失败:{}", throwable.getMessage(), throwable);
    }

}
