package codes.showme.pinecone.cdp.domain.outalator;

import codes.showme.pinecone.cdp.domain.DomainEntity;
import codes.showme.pinecone.cdp.domain.id.IdPrefix;
import codes.showme.pinecone.cdp.domain.outalator.repository.TicketRepository;
import codes.showme.pinecone.cdp.techcommon.idgenerator.IdGenerator;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;
import io.ebean.annotation.DbJsonB;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cdp_outalator_tickets")
public class Ticket implements Serializable, DomainEntity<Ticket> {

    private static final long serialVersionUID = -5433564298672145086L;

    @javax.persistence.Id
    @Column(name = "id", length = ID_LENGTH)
    private String id;

    @Column(name = "summary", columnDefinition = "text")
    private String summary;

    @Column(name = "escalator_level", length = 2)
    private int escalatorLevel = 1;

    @Column(name = "declare_as_incident")
    private boolean declareAsIncident = false;

    @Column(name = "state")
    @Enumerated(value = EnumType.STRING)
    private TicketState state;

    @Column(name = "team_id", length = 32)
    private String teamId;

    @DbJsonB
    @Column(name = "ticket_tags")
    private Set<TicketTag> ticketTags = new HashSet<>();

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ticket_content_type")
    private TicketContentType ticketContentType = TicketContentType.MD;

    /**
     * @return id
     */
    @Override
    public String save() {
        TicketRepository repository = InstanceFactory.getInstance(TicketRepository.class);
        repository.save(this);
        return getId();
    }

    public Pagination<Ticket> paginationBy(String teamTag, PageRequest pageRequest) {
        TicketRepository repository = InstanceFactory.getInstance(TicketRepository.class);
        return repository.paginationBy(teamTag, pageRequest);
    }

    @Override
    public String getIdPrefix() {
        return IdPrefix.TICKET.getVal();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getEscalatorLevel() {
        return escalatorLevel;
    }

    public void setEscalatorLevel(int escalatorLevel) {
        this.escalatorLevel = escalatorLevel;
    }

    public boolean isDeclareAsIncident() {
        return declareAsIncident;
    }

    public void setDeclareAsIncident(boolean declareAsIncident) {
        this.declareAsIncident = declareAsIncident;
    }

    public TicketState getState() {
        return state;
    }

    public void setState(TicketState state) {
        this.state = state;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Set<TicketTag> getTicketTags() {
        return ticketTags;
    }

    public void setTicketTags(Set<TicketTag> ticketTags) {
        this.ticketTags = ticketTags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TicketContentType getTicketContentType() {
        return ticketContentType;
    }

    public void setTicketContentType(TicketContentType ticketContentType) {
        this.ticketContentType = ticketContentType;
    }

}
