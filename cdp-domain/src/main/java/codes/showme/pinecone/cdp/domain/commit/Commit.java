package codes.showme.pinecone.cdp.domain.commit;

import codes.showme.pinecone.cdp.domain.commit.repository.CommitRepository;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import io.ebean.annotation.DbJson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "cdp_commits")
public class Commit {
    @Id
    private String id;

    @Column(name = "short_id", length = 16)
    private String shortId;

    @Column(name = "repo_id")
    private String repoId;

    @Column(name = "namespace", length = 32)
    private String namespace;

    @Column(name = "json_src", columnDefinition = "jsonb")
    @DbJson(name = "json_src")
    private String jsonSrc;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "added_lines")
    private int addedLines;
    @Column(name = "deleted_lines")
    private int deletedLines;
    @Column(name = "changed_lines")
    private int changedLines;

    @Column(name = "message", columnDefinition = "text")
    private String message;

    @Column(name = "title", columnDefinition = "text")
    private String title;

    @Column(name = "commit_at")
    private Date commitAt;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private int year;
    private int month;
    private int date;
    private int hour;
    @Column(name = "day_of_week")
    private int dayOfWeek;
    @Column(name = "zone_id", length = 32)
    private String zoneId;

    public void setCommitAt(Date commitAt) {
        this.commitAt = commitAt;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(commitAt);
        this.zoneId = calendar.getTimeZone().getID();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.date = calendar.get(Calendar.DATE) ;
        this.hour = calendar.get(Calendar.HOUR);
        this.dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    }

    public void save() {
        CommitRepository instance = InstanceFactory.getInstance(CommitRepository.class);
        instance.save(this);
    }

    public String getZoneId() {
        return zoneId;
    }

    public int getYear() {
        return year;
    }


    public int getMonth() {
        return month;
    }


    public int getDate() {
        return date;
    }


    public int getHour() {
        return hour;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }


    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
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

    public String getJsonSrc() {
        return jsonSrc;
    }

    public void setJsonSrc(String jsonSrc) {
        this.jsonSrc = jsonSrc;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCommitAt() {
        return commitAt;
    }


}
