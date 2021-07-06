package codes.showme.pinecone.cdp.domain.artifact.memory.repository;

import codes.showme.pinecone.cdp.domain.artifact.Artifact;
import codes.showme.pinecone.cdp.domain.artifact.repository.ArtifactRepository;
import org.springframework.stereotype.Component;

@Component
public class ArtifactMemoryRepository implements ArtifactRepository {
    @Override
    public long save(Artifact artifact) {
        return 0;
    }
}
