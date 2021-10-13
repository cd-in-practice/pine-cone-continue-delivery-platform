package codes.showme.pinecone.cdp.code.analysis.gitlab;

public interface GitLabCommitEventProcessor {
    void process(SyncGitLabCommitsEvent gitLabCommitsEvent);
}
