package codes.showme.pinecone.cdp.code.analysis.gitlab;

import codes.showme.pinecone.cdp.code.analysis.repository.SyncGitlabDiffEventRepository;
import codes.showme.pinecone.cdp.domain.commit.Commit;
import codes.showme.pinecone.cdp.domain.commit.Diff;
import codes.showme.pinecone.cdp.domain.commit.repository.DiffRepository;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;

import java.util.List;

public class SyncGitlabDiffEvent {
    private Commit commit;

    public SyncGitlabDiffEvent(Commit commit) {
        commit = commit;
    }

    public Commit getCommit() {
        return commit;
    }

    public static void handle(){
        SyncGitlabDiffEventRepository gitlabDiffEventRepository = InstanceFactory.getInstance(SyncGitlabDiffEventRepository.class);
        gitlabDiffEventRepository.handle(new GitLabDiffEventHandler(){
            @Override
            public void handle(List<Diff> diffs) {
                DiffRepository diffRepository = InstanceFactory.getInstance(DiffRepository.class);
                diffRepository.save(diffs);
            }
        });

    }

    public void fire() {
        SyncGitlabDiffEventRepository syncGitlabDiffEventRepository = InstanceFactory.getInstance(SyncGitlabDiffEventRepository.class);
        syncGitlabDiffEventRepository.save(this);
    }
}
