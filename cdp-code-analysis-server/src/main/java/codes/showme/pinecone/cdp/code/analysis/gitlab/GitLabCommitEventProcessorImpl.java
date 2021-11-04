package codes.showme.pinecone.cdp.code.analysis.gitlab;

import codes.showme.pinecone.cdp.code.analysis.domain.SyncGitLabCommitsEvent;
import codes.showme.pinecone.cdp.domain.commit.repository.CommitRepository;
import codes.showme.pinecone.cdp.domain.commit.repository.DiffRepository;
import codes.showme.pinecone.cdp.domain.commit.repository.FileHistoryRepository;
import org.gitlab4j.api.CommitsApi;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.Pager;
import org.gitlab4j.api.models.Commit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class GitLabCommitEventProcessorImpl implements GitLabCommitEventProcessor{

    private static final Logger log = LoggerFactory.getLogger(GitLabCommitEventProcessorImpl.class);

    public static final int pageSize = 10;

    @Resource
    public CommitRepository commitRepository;

    @Resource
    public DiffRepository diffRepository;

    @Resource
    public FileHistoryRepository fileHistoryRepository;

    @Override
    public void process(SyncGitLabCommitsEvent gitLabCommitsEvent) {
        try {
            GitLabApi gitLabApi = getGitLabApi(gitLabCommitsEvent.getGitlabServerUrl(), gitLabCommitsEvent.getToken());
            CommitsApi commitsApi = gitLabApi.getCommitsApi();
            Pager<Commit> commits = commitsApi.getCommits(gitLabCommitsEvent.getGitlabProject(), pageSize);
            while (commits.hasNext()) {
                List<Commit> commitList = commits.next();
                for (Commit commit : commitList) {
                    commitRepository.save(buildBy(commit));
                }
            }
        } catch (GitLabApiException e) {
            log.error("syncCommits error", e);
        }
    }

    private GitLabApi getGitLabApi(String gitLabServerUrl, String token) {
        GitLabApi gitLabApi = new GitLabApi(gitLabServerUrl, token);
        gitLabApi.enableRequestResponseLogging();
        return gitLabApi;
    }

    private codes.showme.pinecone.cdp.domain.commit.Commit buildBy(Commit commit) {
        codes.showme.pinecone.cdp.domain.commit.Commit result = new codes.showme.pinecone.cdp.domain.commit.Commit();
        result.setId(commit.getId());
        result.setAuthorId(commit.getAuthorName());
        result.setCommitAt(commit.getCreatedAt());
        result.setJsonSrc(commit.toString());
        result.setTitle(commit.getTitle());
        result.setMessage(commit.getMessage());
        result.setCreateTime(new Date());
        result.setShortId(commit.getShortId());
        return result;
    }
}
