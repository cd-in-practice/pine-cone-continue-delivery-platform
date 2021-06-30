package codes.showme.pinecone.cdp.artifact.mysql.repository;

import codes.showme.pinecone.cdp.domain.artifact.Artifact;
import codes.showme.pinecone.cdp.domain.artifact.repository.ArtifactRepository;
import io.ebean.Database;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ArtifactMysqlRepository implements ArtifactRepository {

    @Resource
    private Database database;

    @Override
    public long save(Artifact artifact) {
        database.save(artifact);
        return artifact.getId();
    }
}
