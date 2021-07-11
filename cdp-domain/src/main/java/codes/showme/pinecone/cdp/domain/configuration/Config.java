package codes.showme.pinecone.cdp.domain.configuration;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cdp_configs")
public class Config implements Serializable {
    private static final long serialVersionUID = 4695439751292998888L;

    private String appId;

    private String namespace;

    private ConfigYaml configYaml;

}
