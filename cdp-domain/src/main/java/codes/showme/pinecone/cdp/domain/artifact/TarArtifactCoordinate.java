package codes.showme.pinecone.cdp.domain.artifact;

import javax.persistence.Column;

public class TarArtifactCoordinate extends ArtifactCoordinate {

    @Column(name = "tar_url", length = 255)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public ArtifactType getType() {
        return ArtifactType.TAR;
    }
}
