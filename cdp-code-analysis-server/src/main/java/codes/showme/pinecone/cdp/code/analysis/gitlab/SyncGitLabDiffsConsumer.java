package codes.showme.pinecone.cdp.code.analysis.gitlab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SyncGitLabDiffsConsumer implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        SyncGitlabDiffEvent.handle();
    }
}
