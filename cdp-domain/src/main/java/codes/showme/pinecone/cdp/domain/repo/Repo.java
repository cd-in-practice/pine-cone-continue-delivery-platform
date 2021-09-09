package codes.showme.pinecone.cdp.domain.repo;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cdp_repos")
public class Repo implements Serializable {

    private static final long serialVersionUID = 6488199559105753903L;

    @Id
    private String id;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "namespace", length = 32)
    private String namespace;

    @Embedded
    private List<RepoUrl> repoUrls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
