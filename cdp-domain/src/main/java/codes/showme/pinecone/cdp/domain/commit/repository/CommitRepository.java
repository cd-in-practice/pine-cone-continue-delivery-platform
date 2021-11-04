package codes.showme.pinecone.cdp.domain.commit.repository;

import codes.showme.pinecone.cdp.domain.commit.Commit;

import java.util.List;

public interface CommitRepository {
    void save(Commit commit);

    void saveAll(List<Commit> commits);
}
