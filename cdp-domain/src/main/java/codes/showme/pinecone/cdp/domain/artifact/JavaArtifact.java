package codes.showme.pinecone.cdp.domain.artifact;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("java")
public class JavaArtifact extends Artifact{
    @Column(name = "java_repo_id", length = 128)
    private String repoId;
    @Column(name = "java_group_id", length = 50)
    private String groupId;
    @Column(name = "java_artifact_id", length = 50)
    private String artifactId;

    @Column(name = "java_artifact_version", length = 25)
    private String javaArtifactVersion;

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getJavaArtifactVersion() {
        return javaArtifactVersion;
    }

    public void setJavaArtifactVersion(String javaArtifactVersion) {
        this.javaArtifactVersion = javaArtifactVersion;
    }
}
