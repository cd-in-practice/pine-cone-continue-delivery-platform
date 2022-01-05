package codes.showme.pinecone.cdp.domain.outalator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cdp_ticket_events")
public class TicketEvent implements Serializable {

    private static final long serialVersionUID = -485783861304087376L;
    /**
     * creator of the event
     */
    @Column(name = "creator_id")
    private String creatorId;
    @Column(name = "source")
    private String source;
    @Column(name = "event_type")
    private TicketEventType eventType;
    @Column(name = "client")
    private String client;
    @Column(name = "team_id")
    private String teamId;
    @Column(name = "level")
    private int level;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public TicketEventType getEventType() {
        return eventType;
    }

    public void setEventType(TicketEventType eventType) {
        this.eventType = eventType;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
