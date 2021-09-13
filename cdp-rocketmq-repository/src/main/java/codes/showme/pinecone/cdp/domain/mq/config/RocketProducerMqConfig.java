package codes.showme.pinecone.cdp.domain.mq.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rocketmq.producer")
public class RocketProducerMqConfig {
    private String namesrvAddr;
    private String groupName;

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
