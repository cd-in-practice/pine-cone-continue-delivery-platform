package codes.showme.pinecone.cdp.domain.pipeline;

import codes.showme.pinecone.cdp.domain.app.App;
import codes.showme.pinecone.cdp.domain.pipeline.repository.PipelineRepository;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "cdp_pipelines")
public class Pipeline implements Serializable {

    public static final int COLUMN_ID_LENGTH = 32;
    private static final long serialVersionUID = -1969892567479603934L;

    @Id
    @Column(name = "id", length = COLUMN_ID_LENGTH)
    private String id;

    @ElementCollection
    private Set<String> repoIds;

    public Pagination<Pipeline> pagination(String appId, PageRequest pageRequest) {
        PipelineRepository repository = InstanceFactory.getInstance(PipelineRepository.class);
        return repository.pagination(appId, pageRequest);
    }

    public Set<String> getRepoIds() {
        return repoIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
