package codes.showme.pinecone.cdp.domain.outalator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "cdp_ticket_events")
public class TicketEvent {

    /**
     * creator of the event
     */
    private String creatorId;
    private String source;
    private TicketEventType eventType;
    private String client;
    private String teamId;
    private int level;
    private Date createTime;

}
