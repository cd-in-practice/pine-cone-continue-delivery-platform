package codes.showme.pinecone.cdp.domain.deployment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cdp_deployment_history")
public class DeploymentHistory implements Serializable {

    private static final long serialVersionUID = -8303763773138249383L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int number;

    private String appId;

    private String appName;

    private String artifactId;

    /**
     * 同一个app，同一时间可能部署多个版本
     */
    private Set<String> artifactVersionNumber;

    private String appConfigId;

    private String appConfigVersionNumber;

    private String deployConfigId;

    private String deployConfigVersionNumber;

    private String envId;

    private String envName;

    private long duration;

    private Date deploymentTime;

    private Date deploymentEnd;


}
