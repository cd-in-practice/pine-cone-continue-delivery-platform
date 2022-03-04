package codes.showme.pinecone.cdp.domain.artifact;

import codes.showme.pinecone.cdp.domain.DomainEntity;
import codes.showme.pinecone.cdp.domain.app.App;
import codes.showme.pinecone.cdp.domain.artifact.repository.ArtifactRepository;

import codes.showme.pinecone.cdp.domain.pipeline.Pipeline;
import codes.showme.pinecone.cdp.domain.pipeline.PipelineHistory;
import codes.showme.pinecone.cdp.techcommon.idgenerator.IdGenerator;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;
import io.ebean.annotation.DbArray;
import io.ebean.annotation.DbJson;
import io.ebean.annotation.DbJsonB;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static codes.showme.pinecone.cdp.domain.app.App.COLUMN_NAMESPACE_SIZE;


/**
 * A application would have one or some kinds of artifacts.
 */
@Entity
@Table(name = "cdp_artifacts")
public class Artifact implements Serializable, DomainEntity<Artifact> {

    private static final long serialVersionUID = 6414966831562691687L;

    public static final int COLUMN_ID_LENGTH = 32;
    public static final int COLUMN_ARTIFACT_VERSION_LENGTH = 32;

    @Id
    @Column(name = "id", length = COLUMN_ID_LENGTH)
    private String id;

    @Column(name = "name", length = 32)
    private String name;

    @Column(name = "artifact_version", length = COLUMN_ARTIFACT_VERSION_LENGTH)
    private String artifactVersion;

    @Column(name = "build_number")
    private int buildNumber;

    @Column(name = "pipeline_id", length = Pipeline.COLUMN_ID_LENGTH)
    private String pipelineId;

    @Column(name = "pipeline_history_id", length = PipelineHistory.COLUMN_ID_LENGTH)
    private String pipelineHistoryId;

    @Column(name = "app_id", length = App.COLUMN_ID_LENGTH)
    private String appId;

    @Column(name = "namespace", length = COLUMN_NAMESPACE_SIZE)
    private String namespace;

    @Column(name = "commit_id", length = 40)
    private String commitId;

    @Column(name = "md5_hash", length = 32)
    private String md5Hash;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;
//
//    @Column(name = "labels", columnDefinition = "jsonb")
//    @DbJsonB
//    private List<ArtifactLabel> labels = new ArrayList<>();

    @DbJson
    @Column(name = "coordinate", columnDefinition = "jsonb")
    private ArtifactCoordinate coordinate;

    /**
     *
     */
    public static Optional<Artifact> findLatestTestPass(String namespace,
                                                        String appId,
                                                        String name) {

        ArtifactRepository artifactRepository = InstanceFactory.getInstance(ArtifactRepository.class);
        return artifactRepository.findBy(namespace, appId, name, ArtifactLabel.buildTestPassLabel());
    }

    public static Optional<Artifact> findLatestCanary(String namespace,
                                                      String appId,
                                                      String name) {
        ArtifactRepository artifactRepository = InstanceFactory.getInstance(ArtifactRepository.class);
        return artifactRepository.findBy(namespace, appId, name, ArtifactLabel.buildReleaseCanaryLabel());
    }

    public static List<Artifact> paginationByLabels(List<ArtifactLabel> labels) {
        return new ArrayList<>();
    }

    /**
     * 拿到最新的可用的包
     *
     * @return
     */
    public Optional<Artifact> findLatest() {
        return Optional.empty();
    }

    public Optional<Artifact> findCanary() {
        return Optional.empty();
    }

//    public void appendLabelAndSave(String labelName, Object val) {
//        labels.add(new ArtifactLabel(labelName, val));
//        save();
//    }

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

    @Override
    public String getIdPrefix() {
        return "art";
    }

    @Override
    public Serializable save() {
        ArtifactRepository repository = InstanceFactory.getInstance(ArtifactRepository.class);
        if (Objects.isNull(getId())) {
            IdGenerator idGenerator = InstanceFactory.getInstance(IdGenerator.class);
            setId(idGenerator.generate());
        }
        return repository.save(this);
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    public void setMd5Hash(String md5Hash) {
        this.md5Hash = md5Hash;
    }

    public void setCoordinate(ArtifactCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public String getCommitId() {
        return commitId;
    }

    public String getMd5Hash() {
        return md5Hash;
    }

    public ArtifactCoordinate getCoordinate() {
        return coordinate;
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
