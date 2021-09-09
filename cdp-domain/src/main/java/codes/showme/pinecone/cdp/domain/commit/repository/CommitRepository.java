package codes.showme.pinecone.cdp.domain.commit.repository;

import codes.showme.pinecone.cdp.domain.commit.Commit;

public interface CommitRepository {
    void save(Commit commit);
}
