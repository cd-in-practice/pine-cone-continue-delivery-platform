package codes.showme.pinecone.cdp.artifact.mysql.repository;

import codes.showme.pinecone.cdp.domain.artifact.Artifact;
import codes.showme.pinecone.cdp.domain.artifact.repository.ArtifactRepository;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;
import io.ebean.Database;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

@Component
public class ArtifactMysqlRepository implements ArtifactRepository {

    @Resource
    private Database database;

    @Override
    public Serializable save(Artifact artifact) {
        database.save(artifact);
        return artifact.getId();
    }

    @Override
    public <T extends Artifact> Pagination<T> pagination(String appId, PageRequest pageRequest, Class<T> clasz) {
        return null;
    }
}
