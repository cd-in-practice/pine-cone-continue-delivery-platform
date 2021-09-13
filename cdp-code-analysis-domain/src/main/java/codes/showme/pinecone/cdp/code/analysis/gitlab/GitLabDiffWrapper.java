package codes.showme.pinecone.cdp.code.analysis.gitlab;

import io.reflectoring.diffparser.api.DiffParser;
import io.reflectoring.diffparser.api.UnifiedDiffParser;
import org.apache.commons.lang3.StringUtils;
import org.gitlab4j.api.models.Diff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GitLabDiffWrapper {
    private static final Logger log = LoggerFactory.getLogger(GitLabDiffWrapper.class);
    private String commitId;
    private String namespace;
    private String repoId;
    private static final DiffParser diffParser = new UnifiedDiffParser();

    public GitLabDiffWrapper(String commitId, String namespace, String repoId) {
        this.commitId = commitId;
        this.namespace = namespace;
        this.repoId = repoId;
    }

    public codes.showme.pinecone.cdp.domain.commit.Diff buildDiffObj(Diff diff) {
        codes.showme.pinecone.cdp.domain.commit.Diff result = new codes.showme.pinecone.cdp.domain.commit.Diff();
        result.setaMode(diff.getAMode());
        result.setbMode(diff.getBMode());
        String diffContent = null;
        try {
            diffContent = buildDiffContent(diff);

            result.setNewPath(diff.getNewPath());
            result.setOldPath(diff.getOldPath());
            result.setRenamedFile(diff.getRenamedFile());
            result.setDeletedFile(diff.getDeletedFile());
            result.setNewFile(diff.getNewFile());
            result.setNamespace(namespace);
            result.setCommitId(commitId);
            result.setRepoId(repoId);
            result.analysis();
            log.info("diff content:{},result:{}", diffContent, result);
        } catch (IllegalStateException e) {
            log.error("IllegalStateException diff content:{}", diffContent, e);
        }

        return result;
    }

    private String buildDiffContent(Diff diff) {
        if (StringUtils.isNoneBlank(diff.getDiff())) {
            if (diff.getDeletedFile()) {
                return wrapGitLabDiff(diff.getDiff(), diff.getOldPath(), "");
            }
            if (diff.getNewFile()) {
                return wrapGitLabDiff(diff.getDiff(), "", diff.getNewPath());
            }
            return wrapGitLabDiff(diff.getDiff(), diff.getOldPath(), diff.getNewPath());
        }
        return "";
    }


    public List<codes.showme.pinecone.cdp.domain.commit.Diff> buildDiffObj(List<Diff> diffs) {
        List<codes.showme.pinecone.cdp.domain.commit.Diff> result = new ArrayList<>();
        for (Diff diff : diffs) {
            result.add(buildDiffObj(diff));
        }
        return result;
    }


    private String wrapGitLabDiff(String diff, String oldPath, String newPath) {
        if (StringUtils.isBlank(diff)) {
            return "";
        }
        if (StringUtils.isBlank(newPath)) {
            newPath = "/dev/null";
            return "--- a/" + oldPath + "\n" +
                    "+++ " + newPath + "\n" + diff;
        }
        if (StringUtils.isBlank(oldPath)) {
            return "--- " + oldPath + "\n" +
                    "+++ b/" + newPath + "\n" + diff;
        }
        return "--- a/" + oldPath + "\n" +
                "+++ b/" + newPath + "\n" + diff;
    }

}
