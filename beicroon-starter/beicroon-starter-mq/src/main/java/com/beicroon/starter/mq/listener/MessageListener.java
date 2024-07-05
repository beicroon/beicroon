//package com.beicroon.starter.mq.listener;
//
//import com.beicroon.starter.mq.consumer.MqConsumer;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.common.message.MessageExt;
//
//import java.util.List;
//
//@Slf4j
//public class MessageListener implements MessageListenerConcurrently {
//
//    private final MqConsumer<?> listener;
//
//    public MessageListener(MqConsumer<?> listener) {
//        this.listener = listener;
//    }
//
//    @Override
//    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//        for (MessageExt message : messages) {
//            try {
//                this.onConsume(message.getTopic(), message.getMsgId(), message.getBody());
//            } catch (Exception ex) {
//                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//            }
//        }
//
//        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//    }
//
//    private void onConsume(String topic, String msgId, byte[] content) {
//        try {
//            this.listener.consume(content);
//        } catch (Exception ex) {
//            log.error("MQ消息消费失败:topic={}, msgId={}, body={}, error={}", topic, msgId, content, ex.getMessage(), ex);
//
//            throw ex;
//        }
//    }
//
//}
