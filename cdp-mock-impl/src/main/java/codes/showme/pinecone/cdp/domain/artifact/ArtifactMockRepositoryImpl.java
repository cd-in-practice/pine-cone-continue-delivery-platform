package codes.showme.pinecone.cdp.domain.artifact;

import codes.showme.pinecone.cdp.domain.artifact.repository.ArtifactRepository;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Component
public class ArtifactMockRepositoryImpl implements ArtifactRepository {
    @Override
    public Serializable save(Artifact artifact) {
        return UUID.randomUUID().toString();
    }
}
