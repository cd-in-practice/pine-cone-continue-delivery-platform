package codes.showme.pinecone.cdp.domain.commit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cdp_commit_file_history")
public class FileHistory {
    private String id;
    @Column(name = "repo_id")
    private String repoId;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "namespace", length = 32)
    private String namespace;

    @Column(name = "commit_id")
    private String commitId;

    @Column(name = "diff_id")
    private String diffId;

    @Column(name = "file_path", columnDefinition = "text")
    private String filePath;

    @Column(name = "total_lines")
    private int totalLines;

    @Column(name = "extension", length = 32)
    private String extension;

    @Column(name = "added_lines")
    private int addedLines;
    @Column(name = "deleted_lines")
    private int deletedLines;
    @Column(name = "changed_lines")
    private int changedLines;

    private int year;
    private int month;
    private int date;
    private int hour;
    @Column(name = "day_of_week")
    private int dayOfWeek;



}
