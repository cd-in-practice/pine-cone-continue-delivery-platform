package codes.showme.pinecone.cdp.code.analysis;

import codes.showme.pinecone.cdp.code.analysis.GitlabRepoSync;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class GitlabRepoSyncTest {

    @Test
    @Ignore
    public void TestSyncFromGitLab() {

        try (PostgreSQLContainer db = new PostgreSQLContainer(DockerImageName.parse("postgres:12.8"))
                .withDatabaseName("db")) {
            db.start();
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            String jdbc = "jdbc:postgresql://172.18.8.101:30002/cdp";
            String jdbcUrl = db.getJdbcUrl();
            String username = db.getUsername();
            String password = db.getPassword();
            dataSourceConfig.setUrl(jdbc)
                    .setSchema("public")
                    .setPlatform("cdp")
                    .setUsername("cdp")
                    .setPassword("cdp");

            DatabaseConfig config = new DatabaseConfig();
            config.setDefaultServer(true);
            config.setDdlGenerate(true);
            config.setDdlRun(true);
            config.loadFromProperties();
            config.setDataSourceConfig(dataSourceConfig);
            Database database = DatabaseFactory.create(config);
//            CommitPostgresRepository commitRepository = new CommitPostgresRepository();
//            commitRepository.setDatabase(database);
//
//            DiffRepositoryPostgresRepository diffRepositoryPostgresRepository = new DiffRepositoryPostgresRepository();
//            diffRepositoryPostgresRepository.setDatabase(database);
//
//
//            String token = System.getenv("GITLAB_TOKEN");
//            GitlabRepoSync gitlabRepoSync = new GitlabRepoSync();
//            gitlabRepoSync.setDiffRepository(diffRepositoryPostgresRepository);
//            gitlabRepoSync.setCommitRepository(commitRepository);
//            gitlabRepoSync.syncCommits("https://gitlab.com", "zacker330/git-diff-example", token, "default");

        }
    }

}