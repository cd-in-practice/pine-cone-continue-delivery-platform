package codes.showme.pinecone.cdp.code.analysis.domain;

import codes.showme.pinecone.cdp.code.analysis.repository.SyncGitLabCommitsEventRepository;
import codes.showme.pinecone.cdp.domain.commit.Commit;
import codes.showme.pinecone.cdp.techcommon.JsonService;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;

import javax.persistence.*;
import java.io.Serializable;

/**
 * a event to sync commits from gitlab
 */
@Entity
@Table(name = "sync_gitlab_commit_events")
public class SyncGitLabCommitsEvent implements Serializable {

    @Id
    @GeneratedValue
    private String id;

    @Column(name = "sync_gitlab_repo_task_id")
    private String syncGitLabRepoTaskId;

    @Column(name = "gitlab_repo_info_id")
    private String gitLabRepoInfoId;

    @Column(name = "repo_url")
    private String repoUrl;

    public SyncGitLabCommitsEvent(String gitLabRepoInfoId, String repoUrl) {
        this.gitLabRepoInfoId = gitLabRepoInfoId;
        this.repoUrl = repoUrl;
    }

    public void save() {
        SyncGitLabCommitsEventRepository syncGitLabCommitsEventRepository = InstanceFactory.getInstance(SyncGitLabCommitsEventRepository.class);
        syncGitLabCommitsEventRepository.save(this);
    }

    public void fire() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public SyncGitLabCommitsEvent fromJsonByteArr(byte[] src) {
        return InstanceFactory.getInstance(JsonService.class).toObject(src, SyncGitLabCommitsEvent.class);
    }

    public String toJsonString() {
        return InstanceFactory.getInstance(JsonService.class).toJsonString(this);
    }

    public String getGitLabRepoInfoId() {
        return gitLabRepoInfoId;
    }

    public void setGitLabRepoInfoId(String gitLabRepoInfoId) {
        this.gitLabRepoInfoId = gitLabRepoInfoId;
    }
}
