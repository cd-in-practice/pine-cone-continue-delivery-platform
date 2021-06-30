package codes.showme.pinecone.cdp.domain.artifact;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("docker")
public class DockerImageArtifact extends Artifact{

    @Column(name = "docker_repo", length = 255)
    private String repo;

    @Column(name = "docker_tag", length = 64)
    private String tag;


    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
