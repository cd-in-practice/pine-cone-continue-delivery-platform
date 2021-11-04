package codes.showme.pinecone.cdp.code.analysis;

import codes.showme.pinecone.cdp.code.analysis.gitlab.GitLabDiffWrapper;
import codes.showme.pinecone.cdp.domain.commit.repository.CommitRepository;
import codes.showme.pinecone.cdp.domain.commit.repository.DiffRepository;
import codes.showme.pinecone.cdp.domain.commit.repository.FileHistoryRepository;
import org.gitlab4j.api.CommitsApi;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.Pager;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Diff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GitlabRepoSync {

    private static final Logger log = LoggerFactory.getLogger(GitlabRepoSync.class);


}
