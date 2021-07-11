package codes.showme.pinecone.cdp.domain.configuration;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "cdp_app_configs")
public class AppConfig {

    private String appId;

    private String namespace;

    private String versionNumber;

    private String envId;

    private String envName;

    private String content;

}
