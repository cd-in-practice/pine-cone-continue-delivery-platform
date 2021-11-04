package codes.showme.pinecone.cdp.code.analysis.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gitlab_repo_info")
public class GitLabRepoInfo {

    @Id
    @GeneratedValue
    private String id;

    @Column(name = "git_repo_url")
    private String gitRepoUrl;

    @Column(name = "token")
    private String token;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "token_expire_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenExpireTime;

    @Column(name = "verified")
    private boolean verified;


    public GitLabRepoInfo(String gitRepoUrl, String token) {
        this.gitRepoUrl = gitRepoUrl;
        this.token = token;
    }

    public GitLabRepoInfo(String gitRepoUrl, String token, Date tokenExpireTime) {
        this.gitRepoUrl = gitRepoUrl;
        this.token = token;
        this.tokenExpireTime = tokenExpireTime;
    }

    public void save() {

    }

    public static GitLabRepoInfo getById(String gitLabRepoInfoId) {
        return null;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGitRepoUrl() {
        return gitRepoUrl;
    }

    public void setGitRepoUrl(String gitRepoUrl) {
        this.gitRepoUrl = gitRepoUrl;
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

    public Date getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(Date tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }


}
