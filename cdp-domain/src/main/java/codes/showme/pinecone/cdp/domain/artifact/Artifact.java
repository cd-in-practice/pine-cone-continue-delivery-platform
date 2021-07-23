package codes.showme.pinecone.cdp.domain.artifact;

import codes.showme.pinecone.cdp.domain.app.App;
import codes.showme.pinecone.cdp.domain.artifact.repository.ArtifactRepository;

import codes.showme.pinecone.cdp.techcommon.idgenerator.IdGenerator;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;

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

    @Id
    @Column(name = "id", length = 64)
    private String id;

    @Column(name = "name", length = 64)
    private String name;

    /**
     * 制品版本，与制品坐标不同
     */
    @Column(name = "artifact_version", length = 64)
    private String artifactVersion;

    @Column(name = "build_number")
    private int buildNumber;

    @Column(name = "pipeline_id")
    private String pipelineId;

    @Column(name = "pipeline_history_id")
    private String pipelineHistoryId;

    /**
     * 过渡阶段使用
     */
    @Column(name = "pipeline_history_url")
    private String pipelineHistoryUrl;

    @Column(name = "app_id", length = App.COLUMN_ID_LENGTH)
    private String appId;

    @Column(name = "namespace", length = COLUMN_NAMESPACE_SIZE)
    private String namespace;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    /**
     * 暂时为json结构，未来如果需要支持多种结构再扩展
     * 构建过程的元数据是跟制品，还是跟流水线，是一个问题。
     * 这里认为，应该对构建过程中的元数据进行分类。
     * 这里的rawMetadata是制品类的元数据。
     */
    @Column(name = "raw_metadata")
    @Lob
    private String rawMetadata;

    /**
     * 将此制品定义成App
     *
     * @return
     */
    public void defineAsAnApp(String appId) {
        setAppId(id);
        save();
    }

    /**
     * pipeline下的各种制品
     *
     * @param pipelineId
     * @param pageRequest
     * @param clasz
     * @param <T>
     * @return
     */
    public <T extends Artifact> Pagination<T> paginationByPipelineId(String pipelineId, PageRequest pageRequest, Class<T> clasz) {

        return null;
    }


    /**
     * 分页列出一个App的指定类型的制品
     *
     * @param appId
     * @param pageRequest
     * @param clasz
     * @param <T>
     * @return
     */
    public <T extends Artifact> Pagination<T> paginationByAppId(String appId, PageRequest pageRequest, Class<T> clasz) {
        ArtifactRepository repository = InstanceFactory.getInstance(ArtifactRepository.class);
        return repository.pagination(appId, pageRequest, clasz);
    }


    public Serializable save() {
        ArtifactRepository repository = InstanceFactory.getInstance(ArtifactRepository.class);
        if (Objects.isNull(getId())) {
            IdGenerator idGenerator = InstanceFactory.getInstance(IdGenerator.class);
            setId(idGenerator.generate());
        }
        return repository.save(this);
    }

    public String getPipelineHistoryUrl() {
        return pipelineHistoryUrl;
    }

    public void setPipelineHistoryUrl(String pipelineHistoryUrl) {
        this.pipelineHistoryUrl = pipelineHistoryUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    public String getRawMetadata() {
        return rawMetadata;
    }

    public void setRawMetadata(String rawMetadata) {
        this.rawMetadata = rawMetadata;
    }

    public String getPipelineHistoryId() {
        return pipelineHistoryId;
    }

    public void setPipelineHistoryId(String pipelineHistoryId) {
        this.pipelineHistoryId = pipelineHistoryId;
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
