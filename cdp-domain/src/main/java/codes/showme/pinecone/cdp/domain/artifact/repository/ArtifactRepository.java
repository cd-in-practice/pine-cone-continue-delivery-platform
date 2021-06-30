package codes.showme.pinecone.cdp.domain.artifact.repository;

import codes.showme.pinecone.cdp.domain.artifact.Artifact;

public interface ArtifactRepository {

    long save(Artifact artifact);
}
