package codes.showme.pinecone.cdp.domain.artifact;

public class ArtifactLabel {
    private String name;
    private Object val;

    public ArtifactLabel(String name, Object val) {
        this.name = name;
        this.val = val;
    }

    public static ArtifactLabel buildTestPassLabel() {
        return new ArtifactLabel("test", "pass");
    }

    public static ArtifactLabel buildReleaseCanaryLabel() {
        return new ArtifactLabel("release", "canary");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArtifactLabel that = (ArtifactLabel) o;
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        return val != null ? val.equals(that.val) : that.val == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (val != null ? val.hashCode() : 0);
        return result;
    }
}
