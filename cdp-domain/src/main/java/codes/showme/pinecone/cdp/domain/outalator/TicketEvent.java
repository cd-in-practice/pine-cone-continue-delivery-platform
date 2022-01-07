package codes.showme.pinecone.cdp.domain.outalator;

import codes.showme.pinecone.cdp.domain.DomainEntity;
import codes.showme.pinecone.cdp.domain.id.IdPrefix;
import codes.showme.pinecone.cdp.domain.outalator.repository.TicketEventRepository;
import codes.showme.pinecone.cdp.techcommon.idgenerator.IdGenerator;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cdp_ticket_events")
public class TicketEvent implements Serializable, DomainEntity<TicketEvent> {

    private static final long serialVersionUID = -485783861304087376L;

    @javax.persistence.Id
    @Column(name = "id", length = ID_LENGTH)
    private String id;

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

    @Override
    public String getIdPrefix() {
        return IdPrefix.TICKET_EVENT.getVal();
    }

    @Override
    public TicketEvent buildNew() {
        TicketEvent ticketEvent = new TicketEvent();
        IdGenerator idGenerator = InstanceFactory.getInstance(IdGenerator.class);
        ticketEvent.setId(idGenerator.generateWithPrefix(getIdPrefix()));
        return ticketEvent;
    }

    @Override
    public String save() {
        TicketEventRepository ticketEventRepository = InstanceFactory.getInstance(TicketEventRepository.class);
        ticketEventRepository.save(this);
        return getId();
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
