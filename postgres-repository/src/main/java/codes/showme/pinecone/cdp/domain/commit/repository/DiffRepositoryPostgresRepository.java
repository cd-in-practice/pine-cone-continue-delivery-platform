package codes.showme.pinecone.cdp.domain.commit.repository;

import codes.showme.pinecone.cdp.domain.commit.Diff;
import io.ebean.Database;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DiffRepositoryPostgresRepository implements DiffRepository {

    @Resource
    private Database database;

    @Override
    public void save(List<Diff> diffList) {
        database.saveAll(diffList);
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
