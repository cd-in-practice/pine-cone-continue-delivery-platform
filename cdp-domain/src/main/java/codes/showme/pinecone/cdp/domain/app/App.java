package codes.showme.pinecone.cdp.domain.app;

import codes.showme.pinecone.cdp.domain.app.repository.AppRepository;
import codes.showme.pinecone.cdp.techcommon.idgenerator.IdGenerator;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;
import io.ebean.Model;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 虚拟的概念，可以是能被部署的应用、Mobile应用、嵌入式固件
 */
@Entity
@Table(name = "cdp_apps")
public class App  implements Serializable {

    private static final long serialVersionUID = -140694797306671363L;
    public static final int COLUMN_NAMESPACE_SIZE = 32;
    public static final int COLUMN_ID_LENGTH = 32;
    private static final int COLUMN_NAME_LENGTH = 32;

    @Id
    @Column(name = "id", length = COLUMN_ID_LENGTH)
    private String id;

    @Column(name = "name", length = COLUMN_NAME_LENGTH)
    private String name;

    @Column(name = "namespace", length = COLUMN_NAMESPACE_SIZE)
    private String namespace;

    public App() {
    }

    public App(String appName, String namespace) {
        this.name = appName;
        this.namespace = namespace;
    }

    public static Pagination<App> listByNamespace(String namespace, PageRequest pageRequest) {
        if (StringUtils.isEmpty(namespace)) {
            return Pagination.empty();
        }
        AppRepository appRepository = InstanceFactory.getInstance(AppRepository.class);
        return appRepository.listByNamespace(namespace, pageRequest);
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdPrefix() {
        return "app";
    }

    public static App buildNew() {
        IdGenerator idGenerator = InstanceFactory.getInstance(IdGenerator.class);
        App app = new App();
        app.setId(idGenerator.generateWithPrefix(app.getIdPrefix()));
        return app;
    }

    public void save() {
        AppRepository appRepository = InstanceFactory.getInstance(AppRepository.class);
        appRepository.save(this);
    }
}
