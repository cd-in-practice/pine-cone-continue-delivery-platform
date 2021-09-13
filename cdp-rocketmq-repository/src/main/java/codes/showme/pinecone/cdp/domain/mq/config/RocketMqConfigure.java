package codes.showme.pinecone.cdp.domain.mq.config;

import code.showme.pinecone.cdp.event.CdpEventOuterClass;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RocketMqConfigure {

    private static final Logger log = LoggerFactory.getLogger(RocketMqConfigure.class);
    public static final String SYNC_GIT_LAB_COMMITS_CONSUMER = "syncGitLabCommitsConsumer";
    public static final String SYNC_GIT_LAB_COMMITS_PRODUCER = "syncGitLabCommitsProducer";

    @Autowired
    private RocketProducerMqConfig rocketProducerMqConfig;

    @Autowired
    private RocketConsumerMqConfig rocketConsumerMqConfig;

    @Bean(name = SYNC_GIT_LAB_COMMITS_PRODUCER)
    @ConditionalOnProperty(prefix = "rocketmq.producer", value = "default", havingValue = "true")
    public DefaultMQProducer syncGitLabCommitsProducer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(rocketProducerMqConfig.getGroupName());
        producer.setNamesrvAddr(rocketProducerMqConfig.getNamesrvAddr());
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.start();
        log.info("rocketmq producer server started");
        return producer;
    }

    @Bean(name = SYNC_GIT_LAB_COMMITS_CONSUMER)
    @ConditionalOnProperty(prefix = "rocketmq.consumer", value = "default", havingValue = "true")
    public DefaultMQPushConsumer syncGitLabCommitsConsumer() throws MQClientException {

        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketConsumerMqConfig.getGroupName());

        // Specify name server addresses.
        consumer.setNamesrvAddr(rocketConsumerMqConfig.getNamesrvAddr());

        // Subscribe one more more topics to consume.
        consumer.subscribe(CdpEventOuterClass.CdpEvent.EventType.getDescriptor().getFullName(), "*");

        return consumer;

    }
}
