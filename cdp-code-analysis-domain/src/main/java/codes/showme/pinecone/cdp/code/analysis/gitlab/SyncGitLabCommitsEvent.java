package codes.showme.pinecone.cdp.code.analysis.gitlab;

import codes.showme.pinecone.cdp.code.analysis.repository.SyncGitLabCommitsEventRepository;
import codes.showme.pinecone.cdp.techcommon.JsonService;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;

import java.io.Serializable;

/**
 * a event to sync commits from gitlab
 */
public class SyncGitLabCommitsEvent implements Serializable {

    private String namespace;
    private String gitlabServerUrl;
    private String gitlabProject;
    private String token;

    public SyncGitLabCommitsEvent(String namespace, String gitlabServerUrl, String gitlabProject, String token) {
        this.namespace = namespace;
        this.gitlabServerUrl = gitlabServerUrl;
        this.gitlabProject = gitlabProject;
        this.token = token;
    }

    public void save() {
        SyncGitLabCommitsEventRepository syncGitLabCommitsEventRepository = InstanceFactory.getInstance(SyncGitLabCommitsEventRepository.class);
        syncGitLabCommitsEventRepository.save(this);
    }

    public String getNamespace() {
        return namespace;
    }

    public String getGitlabServerUrl() {
        return gitlabServerUrl;
    }

    public String getGitlabProject() {
        return gitlabProject;
    }

    public String getToken() {
        return token;
    }

    public SyncGitLabCommitsEvent fromJsonByteArr(byte[] src) {
        return InstanceFactory.getInstance(JsonService.class).toObject(src, SyncGitLabCommitsEvent.class);
    }

    public String toJsonString() {
        return InstanceFactory.getInstance(JsonService.class).toJsonString(this);
    }
}
