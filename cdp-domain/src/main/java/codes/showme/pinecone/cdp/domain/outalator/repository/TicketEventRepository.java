package codes.showme.pinecone.cdp.domain.outalator.repository;

import codes.showme.pinecone.cdp.domain.outalator.TicketEvent;

public interface TicketEventRepository {
    void save(TicketEvent ticketEvent);
}
