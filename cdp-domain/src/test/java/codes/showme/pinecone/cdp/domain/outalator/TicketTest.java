package codes.showme.pinecone.cdp.domain.outalator;

import codes.showme.pinecone.cdp.domain.id.IdPrefix;
import org.junit.Assert;
import org.junit.Test;

public class TicketTest {

    @Test
    public void createTicketFromAliyunAlert() {
        Ticket ticket = new Ticket();
        ticket.setEscalatorLevel(1);
        ticket.setState(TicketState.OPENED);
        ticket.setSummary("summary of ");
        ticket.save();
    }

    @Test
    public void testGetIdPrefix() {
        Ticket ticket = new Ticket();
        Assert.assertEquals(IdPrefix.TICKET.getVal(), ticket.getIdPrefix());
    }
}
