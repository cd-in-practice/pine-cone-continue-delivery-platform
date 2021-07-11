package codes.showme.pinecone.cdp.domain.pipeline;

import codes.showme.pinecone.cdp.domain.pipeline.repository.PipelineHistoryRepository;
import codes.showme.pinecone.cdp.domain.pipeline.repository.PipelineRepository;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;

import javax.persistence.*;
import java.io.Serializable;

/**
 * pipeline执行历史
 */
@Entity
@Table(name = "cdp_pipeline_history")
public class PipelineHistory implements Serializable {

    private static final long serialVersionUID = 4839444364847751880L;

    @Id
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "result")
    @Enumerated(EnumType.STRING)
    private PipelineExecResult result;

    @Column(name = "url")
    private String url;

    @Column(name = "pipeline_id", length = 32)
    public String pipelineId;

    @Column(name = "scm_commit")
    private String scmCommit;


    @Column(name = "pre_scm_commit")
    private String preScmCommit;

    public Pagination<PipelineHistory> pagination(String appId, PageRequest pageRequest) {
        PipelineHistoryRepository repository = InstanceFactory.getInstance(PipelineHistoryRepository.class);
        return repository.pagination(appId, pageRequest);
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PipelineExecResult getResult() {
        return result;
    }

    public void setResult(PipelineExecResult result) {
        this.result = result;
    }
}
