package codes.showme.pinecone.cdp.domain.artifact;


public abstract class ArtifactCoordinate {
    private ArtifactType type;

    public abstract ArtifactType getType();

    protected void setType(ArtifactType type) {
        this.type = type;
    }
}
