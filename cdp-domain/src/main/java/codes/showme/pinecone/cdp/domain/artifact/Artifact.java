package codes.showme.pinecone.cdp.domain.artifact;

import codes.showme.pinecone.cdp.domain.artifact.repository.ArtifactRepository;

import codes.showme.pinecone.cdp.techcommon.idgenerator.IdGenerator;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import static codes.showme.pinecone.cdp.domain.app.App.COLUMN_NAMESPACE_SIZE;

@MappedSuperclass
@Entity
@Table(name = "cdp_artifacts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "artifact_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Artifact implements Serializable {

    private static final long serialVersionUID = 6414966831562691687L;

    public Serializable save(){
        ArtifactRepository repository = InstanceFactory.getInstance(ArtifactRepository.class);
        if (Objects.isNull(getId())) {
            IdGenerator idGenerator = InstanceFactory.getInstance(IdGenerator.class);
            setId(idGenerator.generate());
        }
        return repository.save(this);
    }

    @Id
    @Column(name = "name")
    private String id;

    /**
     * 制品版本，与制品坐标不同
     */
    @Column(name = "artifact_version", length = 64)
    private String artifactVersion;

    @Column(name = "build_number", length = 16)
    private int buildNumber;

    @Column(name = "pipeline_id")
    private long pipelineId;

    @Column(name = "app_id")
    private long appId;

    @Column(name = "namespace", length = COLUMN_NAMESPACE_SIZE)
    private String namespace;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    public long getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(long pipelineId) {
        this.pipelineId = pipelineId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtifactVersion() {
        return artifactVersion;
    }

    public void setArtifactVersion(String artifactVersion) {
        this.artifactVersion = artifactVersion;
    }

    public int getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(int buildNumber) {
        this.buildNumber = buildNumber;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
