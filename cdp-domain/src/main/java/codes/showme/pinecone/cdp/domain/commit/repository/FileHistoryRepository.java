package codes.showme.pinecone.cdp.domain.commit.repository;

import java.util.List;

public interface FileHistoryRepository {
    void save(List<FileHistoryRepository> repoFileRepositories);
}
