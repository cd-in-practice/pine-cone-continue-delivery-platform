package codes.showme.pinecone.cdp.domain.configuration;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cdp_global_configs")
public class GlobalConfig {

    private String namespace;

    private String versionNumber;

    private String envId;

    private String envName;

    private String content;

}
