package codes.showme.pinecone.cdp.domain.artifact.repository;

import codes.showme.pinecone.cdp.domain.artifact.Artifact;

import java.io.Serializable;

public interface ArtifactRepository {

    Serializable save(Artifact artifact);
}
