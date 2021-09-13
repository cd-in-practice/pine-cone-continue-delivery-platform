package codes.showme.pinecone.cdp.code.analysis.gitlab;

import codes.showme.pinecone.cdp.code.analysis.repository.SyncGitLabCommitsEventRepository;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SyncGitLabCommitsConsumer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SyncGitLabCommitsConsumer.class);

    @Override
    public void run(String... args) throws Exception {
        SyncGitLabCommitsEventRepository syncGitLabCommitsEventRepository = InstanceFactory.getInstance(SyncGitLabCommitsEventRepository.class);
        GitLabCommitEventProcessor gitLabCommitEventProcessor = InstanceFactory.getInstance(GitLabCommitEventProcessor.class);
        syncGitLabCommitsEventRepository.syncGitLabCommitEvents(gitLabCommitEventProcessor);
        log.info("started sync commits from gitlab");
    }
}
