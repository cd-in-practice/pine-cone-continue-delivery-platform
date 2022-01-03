package codes.showme.pinecone.cdp.code.analysis.service;

import codes.showme.pinecone.cdp.code.analysis.gitlab.GitLabDiffWrapper;
import codes.showme.pinecone.cdp.code.analysis.domain.SyncGitLabCommitsEvent;
import codes.showme.pinecone.cdp.code.analysis.gitlab.SyncGitlabDiffEvent;
import codes.showme.pinecone.cdp.domain.commit.repository.CommitRepository;
import codes.showme.pinecone.cdp.domain.commit.repository.DiffRepository;
import codes.showme.pinecone.cdp.domain.commit.repository.FileHistoryRepository;
import org.apache.commons.lang3.RandomUtils;
import org.gitlab4j.api.CommitsApi;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.Pager;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Diff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SyncGitLabServiceImpl implements SyncGitLabService {

    private static final Logger log = LoggerFactory.getLogger(SyncGitLabServiceImpl.class);

    public static final int pageSize = 10;

    @Resource
    public CommitRepository commitRepository;

    @Resource
    public DiffRepository diffRepository;

    @Resource
    public FileHistoryRepository fileHistoryRepository;

    @Override
    public void sync(SyncGitLabRepoReq req, String namespace) {
        syncCommitsAndDiffs(req.getGitLabServerUrl(), req.getGitlabProjectObject(), req.getToken(), namespace);
    }


    public void syncDiffs(String commitId, String gitLabServerUrl, Object gitlabProjectObject, String token, String namespace) {
        try {
            GitLabApi gitLabApi = getGitLabApi(gitLabServerUrl, token);
            CommitsApi commitsApi = gitLabApi.getCommitsApi();
            List<Diff> diffs = commitsApi.getDiff(gitlabProjectObject, commitId);
            if (diffs != null) {
                GitLabDiffWrapper gitLabDiff = new GitLabDiffWrapper(commitId, namespace, gitlabProjectObject.toString());
                diffRepository.save(gitLabDiff.buildDiffObj(diffs));
            } else {
                log.error("syncDiffs error,commitId:{}", commitId);
            }
        } catch (GitLabApiException e) {
            log.error("syncDiffs error", e);
        }
    }

    public void syncCommitsAndDiffs(String gitLabServerUrl, Object gitlabProjectObject, String token, String namespace) {
        try {
            GitLabApi gitLabApi = getGitLabApi(gitLabServerUrl, token);
            CommitsApi commitsApi = gitLabApi.getCommitsApi();
            Pager<Commit> commits = commitsApi.getCommits(gitlabProjectObject, pageSize);
            while (commits.hasNext()) {
                List<Commit> commitList = commits.next();
                if (!Objects.isNull(commitList)) {
                    for (Commit commit : commitList) {
                        codes.showme.pinecone.cdp.domain.commit.Commit gitlabCommit = buildBy(commit, namespace);
                        gitlabCommit.save();
                        List<Diff> diffs = commitsApi.getDiff(gitlabProjectObject, commit.getId());
                        diffRepository.save(buildBy(diffs, commit.getId(), namespace, gitlabProjectObject.toString()));
                    }
                }
            }
        } catch (GitLabApiException e) {
            log.error("syncCommits error", e);
        }
    }

    private List<codes.showme.pinecone.cdp.domain.commit.Diff> buildBy(List<Diff> diffs, String commitId, String namespace, String repoId) {
        if (Objects.isNull(diffs) || diffs.size() == 0) {
            return new ArrayList<>();
        }

        return diffs.stream().map(gitlabDiff -> {
            GitLabDiffWrapper wrapper = new GitLabDiffWrapper(commitId, namespace, repoId);
            codes.showme.pinecone.cdp.domain.commit.Diff diff = wrapper.buildDiffObj(gitlabDiff);
            diff.setId(UUID.randomUUID().toString());
            return diff;
        }).collect(Collectors.toList());
    }


    private GitLabApi getGitLabApi(String gitLabServerUrl, String token) {
        GitLabApi gitLabApi = new GitLabApi(gitLabServerUrl, token);
        gitLabApi.enableRequestResponseLogging();
        return gitLabApi;
    }

    public void setCommitRepository(CommitRepository commitRepository) {
        this.commitRepository = commitRepository;
    }

    public void setDiffRepository(DiffRepository diffRepository) {
        this.diffRepository = diffRepository;
    }

    public void setFileHistoryRepository(FileHistoryRepository fileHistoryRepository) {
        this.fileHistoryRepository = fileHistoryRepository;
    }

    private codes.showme.pinecone.cdp.domain.commit.Commit buildBy(Commit commit, String namespace) {
        codes.showme.pinecone.cdp.domain.commit.Commit result = new codes.showme.pinecone.cdp.domain.commit.Commit();
        result.setId(commit.getId());
        result.setNamespace(namespace);
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
