package codes.showme.pinecone.cdp.domain.commit.repository;

import codes.showme.pinecone.cdp.domain.commit.Diff;

import java.util.List;

public interface DiffRepository {
    void save(List<Diff> diffList);
}
