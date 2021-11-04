package codes.showme.pinecone.cdp.code.analysis.gitlab;

import codes.showme.pinecone.cdp.code.analysis.domain.SyncGitLabCommitsEvent;

public interface GitLabCommitEventProcessor {
    void process(SyncGitLabCommitsEvent gitLabCommitsEvent);
}
