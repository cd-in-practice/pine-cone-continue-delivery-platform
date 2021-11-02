package codes.showme.pinecone.cdp.domain.artifact.mysql.repository;

import codes.showme.pinecone.cdp.domain.commit.repository.FileHistoryRepository;
import io.ebean.Database;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class FileHistoryMysqlRepository implements FileHistoryRepository {
    @Resource
    private Database database;

    @Override
    public void save(List<FileHistoryRepository> repoFileRepositories) {
        database.saveAll(repoFileRepositories);
    }
}
