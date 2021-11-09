package codes.showme.pinecone.cdp.domain.commit.repository;

import codes.showme.pinecone.cdp.domain.commit.Commit;
import io.ebean.Database;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CommitPostgresRepository implements CommitRepository {
    @Resource
    private Database database;

    @Override
    public void save(Commit commit) {
        database.save(commit);
    }

    @Override
    public void saveAll(List<Commit> commits) {
        database.saveAll(commits);
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
