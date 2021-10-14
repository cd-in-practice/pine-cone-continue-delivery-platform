package codes.showme.pinecone.cdp.code.analysis.repository;

import codes.showme.pinecone.cdp.code.analysis.gitlab.GitLabDiffEventHandler;
import codes.showme.pinecone.cdp.code.analysis.gitlab.SyncGitlabDiffEvent;

public interface SyncGitlabDiffEventRepository {
    void handle(GitLabDiffEventHandler gitLabDiffEventHandler);
    void save(SyncGitlabDiffEvent syncGitlabDiffEvent);
}
