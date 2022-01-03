package codes.showme.pinecone.cdp.domain.commit;

import codes.showme.pinecone.cdp.domain.commit.repository.DiffRepository;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import io.reflectoring.diffparser.api.UnifiedDiffParser;
import io.reflectoring.diffparser.api.model.Hunk;
import io.reflectoring.diffparser.api.model.Line;
import io.reflectoring.diffparser.api.model.Range;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Entity
@Table(name = "cdp_diffs")
public class Diff {
    public static final int DIFF_CONENT_SAFE_BYTES_LENGTH = 65532;
    @Id
    @Column(name = "id", length = 64)
    private String id;

    @Column(name = "commit_id", length = 64)
    private String commitId;

    @Column(name = "repo_id", length = 64)
    private String repoId;

    @Column(name = "namespace", length = 32)
    private String namespace;

    @Column(name = "diff_content", columnDefinition = "text")
    private String diffContent;

    @Column(name = "deleted_file")
    private boolean deletedFile;

    @Column(name = "new_file")
    private boolean newFile;
    @Column(name = "renamed_file")
    private boolean renamedFile;
    @Column(name = "new_path", length = 128)
    private String newPath;
    @Column(name = "old_path", length = 128)
    private String oldPath;
    @Column(name = "a_mode", length = 8)
    private String aMode;
    @Column(name = "b_mode", length = 8)
    private String bMode;

    @Column(name = "added_lines")
    private int addedLines = 0;
    @Column(name = "deleted_lines")
    private int deletedLines = 0;
    @Column(name = "changed_lines")
    private int changedLines = 0;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "diff_bytes")
    private long diffBytesLength;

    private static final Pattern CHANGED_LINE_PATTERN = Pattern.compile("-\\+");

    public void analysis() {

        if (StringUtils.isBlank(diffContent)) {
            return;
        }

        analysisLines();

        makeSureDiffContentSizeSafe();
    }

    private void makeSureDiffContentSizeSafe() {
        this.diffBytesLength = diffContent.getBytes().length;
        if (diffBytesLength > DIFF_CONENT_SAFE_BYTES_LENGTH) {
            diffContent = "";
        }
    }

    private void analysisLines() {
        List<io.reflectoring.diffparser.api.model.Diff> diffList =
                new UnifiedDiffParser().parse(diffContent.getBytes(StandardCharsets.UTF_8));
        for (io.reflectoring.diffparser.api.model.Diff reflectorDiff : diffList) {
            for (Hunk hunk : reflectorDiff.getHunks()) {
                if (isNewFile()) {
                    Range toFileRange = hunk.getToFileRange();
                    this.addedLines += toFileRange.getLineCount();
                    break;
                }

                if (isRenamedFile()) {
                    break;
                }

                if (isDeletedFile()) {
                    Range fromFileRange = hunk.getFromFileRange();
                    this.deletedLines += fromFileRange.getLineCount();
                    break;
                }

                int toCount = (int) hunk.getLines().stream().filter(line -> Line.LineType.TO.equals(line.getLineType())).count();
                int fromCount = (int) hunk.getLines().stream().filter(line -> Line.LineType.FROM.equals(line.getLineType())).count();

                String lineTypesStr = hunk.getLines().stream()
                        .map(line -> {
                            if (Line.LineType.TO.equals(line.getLineType())) {
                                return "+";
                            }
                            if (Line.LineType.FROM.equals(line.getLineType())) {
                                return "-";
                            }
                            return " ";
                        }).collect(Collectors.joining(""));

                this.changedLines += calculateChangedLines(lineTypesStr);
                this.deletedLines += (fromCount - this.changedLines);
                this.addedLines += (toCount - this.changedLines);
            }
        }
    }

    private int calculateChangedLines(String lineTypesStr) {
        int count = 0;
        Matcher matcher = CHANGED_LINE_PATTERN.matcher(lineTypesStr);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public static void saveAll(List<Diff> diffs) {
        DiffRepository diffRepository = InstanceFactory.getInstance(DiffRepository.class);
        diffRepository.save(diffs);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getDiffContent() {
        return diffContent;
    }

    public void setDiffContent(String diffContent) {
        this.diffContent = diffContent;
    }

    public int getAddedLines() {
        return addedLines;
    }

    public void setAddedLines(int addedLines) {
        this.addedLines = addedLines;
    }

    public int getDeletedLines() {
        return deletedLines;
    }

    public void setDeletedLines(int deletedLines) {
        this.deletedLines = deletedLines;
    }

    public int getChangedLines() {
        return changedLines;
    }

    public void setChangedLines(int changedLines) {
        this.changedLines = changedLines;
    }

    public boolean isDeletedFile() {
        return deletedFile;
    }

    public void setDeletedFile(boolean deletedFile) {
        this.deletedFile = deletedFile;
    }

    public boolean isNewFile() {
        return newFile;
    }

    public void setNewFile(boolean newFile) {
        this.newFile = newFile;
    }

    public boolean isRenamedFile() {
        return renamedFile;
    }

    public void setRenamedFile(boolean renamedFile) {
        this.renamedFile = renamedFile;
    }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public String getaMode() {
        return aMode;
    }

    public void setaMode(String aMode) {
        this.aMode = aMode;
    }

    public String getbMode() {
        return bMode;
    }

    public void setbMode(String bMode) {
        this.bMode = bMode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Diff{" +
                "id='" + id + '\'' +
                ", commitId='" + commitId + '\'' +
                ", repoId='" + repoId + '\'' +
                ", namespace='" + namespace + '\'' +
                ", diffContent='" + diffContent + '\'' +
                ", deletedFile=" + deletedFile +
                ", newFile=" + newFile +
                ", renamedFile=" + renamedFile +
                ", newPath='" + newPath + '\'' +
                ", oldPath='" + oldPath + '\'' +
                ", aMode='" + aMode + '\'' +
                ", bMode='" + bMode + '\'' +
                ", addedLines=" + addedLines +
                ", deletedLines=" + deletedLines +
                ", changedLines=" + changedLines +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public long getDiffBytesLength() {
        return diffBytesLength;
    }

    public void setDiffBytesLength(long diffBytesLength) {
        this.diffBytesLength = diffBytesLength;
    }
}
