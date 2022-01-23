package codes.showme.pinecone.cdp.domain.artifact.repository;

import codes.showme.pinecone.cdp.domain.artifact.Artifact;
import codes.showme.pinecone.cdp.domain.artifact.ArtifactLabel;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;

import java.io.Serializable;
import java.util.Optional;

public interface ArtifactRepository {

    Serializable save(Artifact artifact);

    <T extends Artifact> Pagination<T> pagination(String appId, PageRequest pageRequest, Class<T> clasz);

    <T extends Artifact> Optional<T> findBy(String namespace, String appId, String name, ArtifactLabel buildTestPass);

}
