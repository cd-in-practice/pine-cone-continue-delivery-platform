package codes.showme.pinecone.cdp.code.analysis;

import codes.showme.pinecone.cdp.domain.commit.repository.CommitPostgresRepository;
import codes.showme.pinecone.cdp.domain.commit.repository.DiffRepositoryPostgresRepository;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class SyncTest {

    @Test
    @Ignore
    public void TestSyncFromGitLab() {

        try (PostgreSQLContainer db = new PostgreSQLContainer(DockerImageName.parse("postgres:12.8"))
                .withDatabaseName("db")) {
            db.start();
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setUrl(db.getJdbcUrl())
                    .setPlatform("postgres")
                    .setUsername(db.getUsername())
                    .setPassword(db.getPassword());

            DatabaseConfig config = new DatabaseConfig();
            config.setDefaultServer(true);
            config.setDdlGenerate(true);
            config.setDdlRun(true);
            config.loadFromProperties();
            config.setDataSourceConfig(dataSourceConfig);
            Database database = DatabaseFactory.create(config);
            CommitPostgresRepository commitRepository = new CommitPostgresRepository();
            commitRepository.setDatabase(database);

            DiffRepositoryPostgresRepository diffRepositoryPostgresRepository = new DiffRepositoryPostgresRepository();
            diffRepositoryPostgresRepository.setDatabase(database);


            String token = System.getenv("GITLAB_TOKEN");
            Sync sync = new Sync();
            sync.setDiffRepository(diffRepositoryPostgresRepository);
            sync.setCommitRepository(commitRepository);
            sync.syncCommits("https://gitlab.com", "zacker330/git-diff-example", token, "default");

        }
    }

}