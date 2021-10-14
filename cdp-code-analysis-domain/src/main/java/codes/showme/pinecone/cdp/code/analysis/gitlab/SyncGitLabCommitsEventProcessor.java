package codes.showme.pinecone.cdp.code.analysis.gitlab;

import codes.showme.pinecone.cdp.domain.commit.Commit;

public interface SyncGitLabCommitsEventProcessor {
    void process(Commit commit);
}
