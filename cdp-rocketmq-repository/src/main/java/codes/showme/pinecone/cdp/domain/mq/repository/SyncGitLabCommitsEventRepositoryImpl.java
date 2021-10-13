package codes.showme.pinecone.cdp.domain.mq.repository;

import code.showme.pinecone.cdp.event.CdpEventOuterClass;
import codes.showme.pinecone.cdp.code.analysis.gitlab.GitLabCommitEventProcessor;
import codes.showme.pinecone.cdp.code.analysis.gitlab.SyncGitLabCommitsEvent;
import codes.showme.pinecone.cdp.code.analysis.repository.SyncGitLabCommitsEventRepository;
import codes.showme.pinecone.cdp.domain.mq.config.RocketMqConfigure;
import codes.showme.pinecone.cdp.techcommon.JsonService;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static codes.showme.pinecone.cdp.domain.mq.config.RocketMqConfigure.SYNC_GIT_LAB_COMMITS_PRODUCER;


@Component
public class SyncGitLabCommitsEventRepositoryImpl implements SyncGitLabCommitsEventRepository {

    private static final Logger log = LoggerFactory.getLogger(SyncGitLabCommitsEventRepositoryImpl.class);

    @Resource
    private RocketMqConfigure rocketMqConfigure;

    @Override
    public void syncGitLabCommitEvents(GitLabCommitEventProcessor gitLabCommitEventProcessor) {
        try {
            JsonService jsonService = InstanceFactory.getInstance(JsonService.class);
            DefaultMQPushConsumer consumer = InstanceFactory.getInstance(DefaultMQPushConsumer.class, RocketMqConfigure.SYNC_GIT_LAB_COMMITS_CONSUMER);
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt msg : msgs) {
                        gitLabCommitEventProcessor.process(jsonService.toObject(msg.getBody(), SyncGitLabCommitsEvent.class));
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(SyncGitLabCommitsEvent syncGitLabCommitsEvent) {
        try {
            Message message = buildSyncCommitEventMsg(syncGitLabCommitsEvent);
            DefaultMQProducer defaultMQProducer = InstanceFactory.getInstance(DefaultMQProducer.class, SYNC_GIT_LAB_COMMITS_PRODUCER);
            defaultMQProducer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("syncGitLabCommitsEvent sent successful,msgId:{}", sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable throwable) {
                    log.info("syncGitLabCommitsEvent sent failed,event:{}", syncGitLabCommitsEvent, throwable);
                }
            });
        } catch (MQClientException e) {
            log.error("MQClientException, event:{}", syncGitLabCommitsEvent, e);
        } catch (RemotingException e) {
            log.error("RemotingException, event:{}", syncGitLabCommitsEvent, e);
        } catch (InterruptedException e) {
            log.error("InterruptedException, event:{}", syncGitLabCommitsEvent, e);
        }
    }

    private Message buildSyncCommitEventMsg(SyncGitLabCommitsEvent syncGitLabCommitsEvent) {
        return new Message(
                CdpEventOuterClass.CdpEvent.EventType.COMMIT_SYNC_EVENT.getDescriptorForType().getFullName(),
                syncGitLabCommitsEvent.toJsonString().getBytes(StandardCharsets.UTF_8)
        );
    }
}
