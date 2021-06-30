package codes.showme.pinecone.cdp.domain.artifact;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("tar")
public class TarArtifact extends Artifact{

    @Column(name = "tar_url", length = 255)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
