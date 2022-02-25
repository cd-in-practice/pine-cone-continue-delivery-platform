package codes.showme.pinecone.cdp.domain;

import codes.showme.pinecone.cdp.domain.app.App;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.bean.EntityBean;
import io.ebean.config.DatabaseConfig;
import io.ebean.config.dbplatform.DatabasePlatform;
import io.ebean.datasource.DataSourceConfig;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Arrays;

/**
 * just for generate sql ddl
 */
public class DDLGenerate {

    @Rule
    public PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>(POSTGRES_12_8)
            .withUsername("testcontainers")
            .withPassword("testcontainers")
            .withDatabaseName("tescontainers");

    public static final String POSTGRES_12_8 = "postgres:12.8";

    @Test
    public void name() {



        DatabaseConfig config = new DatabaseConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUsername(postgreSQLContainer.getUsername());
        dataSourceConfig.setPassword(postgreSQLContainer.getPassword());
        dataSourceConfig.setUrl(postgreSQLContainer.getJdbcUrl());
        config.setDdlCreateOnly(true);
        config.setPackages(Arrays.asList("codes.showme.pinecone.cdp.domain"));
        config.setDataSourceConfig(dataSourceConfig);

        Database database = DatabaseFactory.create(config);
        App app = new App();
        app.setId("app_id");
        database.save(app);

    }
}
