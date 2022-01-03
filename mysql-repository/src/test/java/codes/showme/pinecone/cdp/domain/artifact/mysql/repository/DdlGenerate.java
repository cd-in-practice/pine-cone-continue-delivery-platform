package codes.showme.pinecone.cdp.domain.artifact.mysql.repository;

import codes.showme.pinecone.cdp.domain.artifact.Artifact;
import codes.showme.pinecone.cdp.domain.artifact.JavaArtifact;
import codes.showme.pinecone.cdp.domain.artifact.TarArtifact;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.xml.crypto.Data;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DdlGenerate {

    public static final String DB = "db";

    @Test
    public void generate() {
        try (MySQLContainer mysql = new MySQLContainer(DockerImageName.parse("mysql:8.0.25"))
                .withDatabaseName(DB)) {
            mysql.start();
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setUrl(mysql.getJdbcUrl())
                    .setPlatform("mysql")
                    .setUsername(mysql.getUsername())
                    .setPassword(mysql.getPassword());

            DatabaseConfig config = new DatabaseConfig();
            config.setDefaultServer(true);
            config.setDdlGenerate(true);
            config.setDdlRun(true);
            config.loadFromProperties();
            config.setDataSourceConfig(dataSourceConfig);
            Database database = DatabaseFactory.create(config);

            TarArtifact artifact = new TarArtifact();
            artifact.setUrl("url");
            artifact.setId("abc");
            database.save(artifact);

        }
    }
}