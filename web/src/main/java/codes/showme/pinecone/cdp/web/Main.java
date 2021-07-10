package codes.showme.pinecone.cdp.web;

import codes.showme.pinecone.cdp.ioc.SpringInstanceProvider;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"codes.showme.pinecone.cdp"})
public class Main implements ApplicationContextAware {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        InstanceFactory.setInstanceProvider(new SpringInstanceProvider(applicationContext));
    }
}
