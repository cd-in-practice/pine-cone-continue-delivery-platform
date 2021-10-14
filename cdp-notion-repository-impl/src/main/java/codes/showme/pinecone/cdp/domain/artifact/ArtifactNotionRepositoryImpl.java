package codes.showme.pinecone.cdp.domain.artifact;

import codes.showme.pinecone.cdp.domain.artifact.repository.ArtifactRepository;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("ArtifactNotionRepositoryImpl")
public class ArtifactNotionRepositoryImpl implements ArtifactRepository {


    @Override
    public Serializable save(Artifact artifact) {



        return null;
    }

    @Override
    public <T extends Artifact> Pagination<T> pagination(String appId, PageRequest pageRequest, Class<T> clasz) {
        return null;
    }
}
