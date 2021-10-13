package codes.showme.pinecone.cdp.code.analysis.repository;

import codes.showme.pinecone.cdp.code.analysis.gitlab.GitLabCommitEventProcessor;
import codes.showme.pinecone.cdp.code.analysis.gitlab.SyncGitLabCommitsEvent;

public interface SyncGitLabCommitsEventRepository {
    void syncGitLabCommitEvents(GitLabCommitEventProcessor gitLabCommitEventProcessor);
    void save(SyncGitLabCommitsEvent syncGitLabCommitsEvent);
}
