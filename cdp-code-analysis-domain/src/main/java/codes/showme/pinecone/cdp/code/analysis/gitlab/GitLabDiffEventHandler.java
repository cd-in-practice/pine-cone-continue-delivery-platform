package codes.showme.pinecone.cdp.code.analysis.gitlab;

import codes.showme.pinecone.cdp.domain.commit.Diff;

import java.util.List;

public interface GitLabDiffEventHandler {
    void handle(List<Diff> diffs);
}
