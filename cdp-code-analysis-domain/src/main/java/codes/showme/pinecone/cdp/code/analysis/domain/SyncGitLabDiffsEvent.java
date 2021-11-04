package codes.showme.pinecone.cdp.code.analysis.domain;

import javax.persistence.*;

@Entity
@Table(name = "sync_gitlab_diffs_events")
public class SyncGitLabDiffsEvent {
    @Id
    @GeneratedValue
    private String id;

    @Column(name = "commit_id")
    private String commitId;

    @Column(name = "repo_url")
    private String repoUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }
}
