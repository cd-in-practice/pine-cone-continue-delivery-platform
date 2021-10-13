package codes.showme.pinecone.cdp.domain.commit.repository.config;

import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class EbeanFactory implements FactoryBean<Database> {

    @Override
    public Database getObject() throws Exception {
        DatabaseConfig config = new DatabaseConfig();
        config.setName("db");
        config.loadFromProperties();
        return DatabaseFactory.create(config);
    }
    @Override
    public Class<?> getObjectType() {
        return Database.class;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }
}
