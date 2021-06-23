package codes.showme.cds.artifact.mysql.repository;

import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.xml.crypto.Data;

import static org.junit.Assert.*;

public class DdlGenerate {

    public static final String DB = "db";

    @Test
    public void generate() {
        try(MySQLContainer mysql = new MySQLContainer(DockerImageName.parse("mysql:8.0.25"))
                .withDatabaseName(DB)) {
            mysql.start();
            DatabaseConfig config = new DatabaseConfig();
            config.setName(DB);
            config.setDdlGenerate(true);
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setUrl(mysql.getJdbcUrl())
                    .setUsername(mysql.getUsername())
                    .setPassword(mysql.getPassword());
            config.setDataSourceConfig(dataSourceConfig);
            DatabaseFactory.create(config);
        }
    }
}