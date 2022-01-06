package codes.showme.pinecone.cdp.domain.outalator.repository;

import codes.showme.pinecone.cdp.domain.outalator.Ticket;

public interface TicketRepository {
    void save(Ticket ticket);
}
