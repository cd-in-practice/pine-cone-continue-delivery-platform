package codes.showme.pinecone.cdp.domain.configuration;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cdp_envs")
public class Env {

    private String id;
    private String name;
    private String orderNum;
    private String description;
    private String namespace;

}
