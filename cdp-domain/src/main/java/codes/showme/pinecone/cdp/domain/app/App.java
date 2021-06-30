package codes.showme.pinecone.cdp.domain.app;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cdp_apps")
public class App implements Serializable {

    private static final long serialVersionUID = -140694797306671363L;
    public static final int COLUMN_NAMESPACE_SIZE = 32;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Version
    private Long version;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "namespace", length = COLUMN_NAMESPACE_SIZE)
    private String namespace;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
