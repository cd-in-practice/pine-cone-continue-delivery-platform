package codes.showme.pinecone.cdp.domain.artifact;


public class DockerImageArtifactCoordinate extends ArtifactCoordinate {

    private String repo;

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


    @Override
    public ArtifactType getType() {
        return ArtifactType.DOCKER_IMAGE;
    }
}
