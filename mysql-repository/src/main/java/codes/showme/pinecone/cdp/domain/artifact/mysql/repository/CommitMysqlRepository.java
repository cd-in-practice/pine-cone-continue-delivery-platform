package codes.showme.pinecone.cdp.domain.artifact.mysql.repository;

import codes.showme.pinecone.cdp.domain.commit.Commit;
import codes.showme.pinecone.cdp.domain.commit.repository.CommitRepository;
import io.ebean.Database;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CommitMysqlRepository implements CommitRepository {

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
}