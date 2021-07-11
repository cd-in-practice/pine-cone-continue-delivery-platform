package codes.showme.pinecone.cdp.domain.configuration;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cdp_deploy_configs")
public class DeployConfig {

    private String appId;

    private String namespace;

    private String envId;

    private String envName;

    private String versionNumber;

    private String content;

}
