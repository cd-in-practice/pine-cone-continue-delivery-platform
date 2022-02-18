package codes.showme.pinecone.cdp.domain;


import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * just for generate sql ddl
 */
public class DDLGenerate {


    public static final String POSTGRES_12_8 = "postgres:12.8";

    @Test
    public void name() {
        try(PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>(POSTGRES_12_8)
                .withUsername("testcontainers")
                .withPassword("testcontainers")
                .withDatabaseName("tescontainers")){
            postgreSQLContainer.start();

            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setUsername("sa");
            dataSourceConfig.setPassword("");
            dataSourceConfig.setUrl("jdbc:h2:mem:myapp;");


            DatabaseConfig config = new DatabaseConfig();
            config.setDataSourceConfig(dataSourceConfig);

// create database instance
            Database database = DatabaseFactory.create(config);

        }catch(Exception e){

        }

    }
}
