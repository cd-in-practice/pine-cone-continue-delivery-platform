package codes.showme.cds.artifact.mysql.repository;

import codes.showme.cds.domain.artifact.Artifact;
import codes.showme.cds.domain.artifact.repository.ArtifactRespository;
import io.ebean.Database;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ArtifactMysqlRespository implements ArtifactRespository {

    @Resource
    private Database database;

    public long save(Artifact artifact) {
        database.save(artifact);
        return artifact.getId();
    }
}
