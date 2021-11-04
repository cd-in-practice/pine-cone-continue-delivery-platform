package codes.showme.pinecone.cdp.code.analysis.domain;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "cdp_sync_tasks")
public class SyncGitLabRepoTask implements Serializable {

    private static final long serialVersionUID = -8441399239647134400L;

    @Id
    @GeneratedValue
    private String id;

    /**
     * gitLabRepoInfoId or gitRepoUrl,token
     */
    @Column(name = "gitlab_repo_info_id")
    private String gitLabRepoInfoId;

    @Column(name = "repo_url")
    private String repoUrl;

    @Column(name = "token")
    private String token;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    public SyncGitLabRepoTask(String gitLabRepoInfoId, String repoUrl, String token) {
        this.gitLabRepoInfoId = gitLabRepoInfoId;
        this.repoUrl = repoUrl;
        this.token = token;
    }

    public static Serializable save(SyncGitLabRepoTask syncGitLabRepoTask) {
        return null;
    }

    public void run() {
        GitLabRepoInfo gitLabRepoInfo = null;
        if (existsGitLabRepoId()) {
            gitLabRepoInfo = GitLabRepoInfo.getById(gitLabRepoInfoId);
        } else {
            gitLabRepoInfo = new GitLabRepoInfo(repoUrl, token);
            gitLabRepoInfo.save();
        }
        new SyncGitLabCommitsEvent(gitLabRepoInfo.getId(), gitLabRepoInfo.getGitRepoUrl(), gitLabRepoInfo.getToken()).fire();
    }

    private boolean existsGitLabRepoId() {
        return StringUtils.isNoneBlank(gitLabRepoInfoId);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGitLabRepoInfoId() {
        return gitLabRepoInfoId;
    }

    public void setGitLabRepoInfoId(String gitLabRepoInfoId) {
        this.gitLabRepoInfoId = gitLabRepoInfoId;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
