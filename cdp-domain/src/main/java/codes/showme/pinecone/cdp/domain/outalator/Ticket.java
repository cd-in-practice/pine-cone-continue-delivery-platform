package codes.showme.pinecone.cdp.domain.outalator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "cdp_outalator_tickets")
public class Ticket {
    @Id
    @Column(name = "id", length = 32)
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

    private Map<String, String> tags = new HashMap<>();

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ticket_content_type")
    private TicketContentType ticketContentType = TicketContentType.MD;




}
