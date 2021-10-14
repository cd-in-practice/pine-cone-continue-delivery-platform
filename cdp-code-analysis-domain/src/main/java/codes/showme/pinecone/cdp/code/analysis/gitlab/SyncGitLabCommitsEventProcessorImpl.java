package codes.showme.pinecone.cdp.code.analysis.gitlab;

import codes.showme.pinecone.cdp.domain.commit.Commit;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import org.springframework.stereotype.Component;

@Component
public class SyncGitLabCommitsEventProcessorImpl implements SyncGitLabCommitsEventProcessor {

    @Override
    public void process(Commit commit) {
        // 这里存在事务问题
        commit.save();
        new SyncGitlabDiffEvent(commit).fire();
    }
}
