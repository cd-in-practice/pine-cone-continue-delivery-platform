package codes.showme.pinecone.cdp.domain.deployment;

import io.ebean.annotation.DbJsonB;

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
    @DbJsonB
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public Set<String> getArtifactVersionNumber() {
        return artifactVersionNumber;
    }

    public void setArtifactVersionNumber(Set<String> artifactVersionNumber) {
        this.artifactVersionNumber = artifactVersionNumber;
    }

    public String getAppConfigId() {
        return appConfigId;
    }

    public void setAppConfigId(String appConfigId) {
        this.appConfigId = appConfigId;
    }

    public String getAppConfigVersionNumber() {
        return appConfigVersionNumber;
    }

    public void setAppConfigVersionNumber(String appConfigVersionNumber) {
        this.appConfigVersionNumber = appConfigVersionNumber;
    }

    public String getDeployConfigId() {
        return deployConfigId;
    }

    public void setDeployConfigId(String deployConfigId) {
        this.deployConfigId = deployConfigId;
    }

    public String getDeployConfigVersionNumber() {
        return deployConfigVersionNumber;
    }

    public void setDeployConfigVersionNumber(String deployConfigVersionNumber) {
        this.deployConfigVersionNumber = deployConfigVersionNumber;
    }

    public String getEnvId() {
        return envId;
    }

    public void setEnvId(String envId) {
        this.envId = envId;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Date getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Date deploymentTime) {
        this.deploymentTime = deploymentTime;
    }

    public Date getDeploymentEnd() {
        return deploymentEnd;
    }

    public void setDeploymentEnd(Date deploymentEnd) {
        this.deploymentEnd = deploymentEnd;
    }
}
