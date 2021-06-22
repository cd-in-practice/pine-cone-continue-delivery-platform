package codes.showme.cds.domain.artifact.repository;

import codes.showme.cds.domain.artifact.Artifact;

public interface ArtifactRespository {

    long save(Artifact artifact);
}
