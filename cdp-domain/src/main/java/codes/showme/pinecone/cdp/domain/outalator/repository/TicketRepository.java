package codes.showme.pinecone.cdp.domain.outalator.repository;

import codes.showme.pinecone.cdp.domain.outalator.Ticket;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;

public interface TicketRepository {
    void save(Ticket ticket);

    Pagination<Ticket> paginationBy(String teamTag, PageRequest pageRequest);
}
