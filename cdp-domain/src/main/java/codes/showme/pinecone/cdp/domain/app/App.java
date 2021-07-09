package codes.showme.pinecone.cdp.domain.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cdp_apps")
public class App implements Serializable {

    private static final long serialVersionUID = -140694797306671363L;
    public static final int COLUMN_NAMESPACE_SIZE = 32;

    @Id
    @Column(name = "id", length = 32)
    private String id;

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
}
