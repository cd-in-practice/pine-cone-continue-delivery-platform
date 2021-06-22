package codes.showme.cds.artifact.mysql.repository.config;

import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class EbeanFactoryEbean implements FactoryBean<Database> {

    public Database getObject() throws Exception {
        DatabaseConfig config = new DatabaseConfig();
        config.setName("db");
        config.loadFromProperties();
        return DatabaseFactory.create(config);
    }

    public Class<?> getObjectType() {
        return Database.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
