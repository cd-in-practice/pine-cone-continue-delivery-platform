package codes.showme.pinecone.cdp.domain.commit.repository;

import io.ebean.Database;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class FileHistoryPostgresRepository implements FileHistoryRepository {

    @Resource
    private Database database;

    @Override
    public void save(List<FileHistoryRepository> repoFileRepositories) {
        database.saveAll(repoFileRepositories);
    }
}
