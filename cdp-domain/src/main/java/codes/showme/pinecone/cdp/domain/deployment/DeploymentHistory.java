package codes.showme.pinecone.cdp.domain.deployment;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cdp_deployment_history")
public class DeploymentHistory implements Serializable {

    private static final long serialVersionUID = -8303763773138249383L;

    private String appId;

    private String artifactId;

    private long durationMiles;

    private Date deploymentTime;

    private Date deploymentEnd;




}
