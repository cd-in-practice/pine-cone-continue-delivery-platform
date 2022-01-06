package codes.showme.pinecone.cdp.domain.outalator;

import org.junit.Test;

public class TicketTest {

    @Test
    public void createTicketFromAliyunAlert() {
        Ticket ticket = new Ticket();
        ticket.setEscalatorLevel(1);
        ticket.setState(TicketState.OPENED);
        ticket.setSummary("summary of ");
    }

}
