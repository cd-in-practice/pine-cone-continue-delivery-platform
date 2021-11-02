package codes.showme.pinecone.cdp.domain.artifact.mysql.repository;

import codes.showme.pinecone.cdp.domain.commit.Diff;
import codes.showme.pinecone.cdp.domain.commit.repository.DiffRepository;
import io.ebean.Database;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DiffMysqlRepository implements DiffRepository {
    @Resource
    private Database database;

    @Override
    public void save(List<Diff> diffList) {
        database.saveAll(diffList);
    }
}
