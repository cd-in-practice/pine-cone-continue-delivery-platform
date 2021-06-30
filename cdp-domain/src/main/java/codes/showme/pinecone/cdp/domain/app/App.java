package codes.showme.pinecone.cdp.domain.app;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cdp_apps")
public class App implements Serializable {

    private static final long serialVersionUID = -140694797306671363L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Version
    private Long version;

    @Column(name = "name", length = 64)
    private String name;

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
