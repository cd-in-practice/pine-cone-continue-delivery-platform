package codes.showme.pinecone.cdp.domain.commit;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class CommitTest {

    @Test
    public void setCommitAt() {

        String zone = "Asia/Shanghai";
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(zone));
        // Tue Sep 07 07:01:11 CST 2021
        calendar.set(2021, 8, 7, 7, 1);

        Commit commit = new Commit();
        commit.setCommitAt(calendar.getTime());
        assertEquals(2021, commit.getYear());
        assertEquals(9, commit.getMonth());
        assertEquals(7, commit.getDate());
        assertEquals(7, commit.getHour());
        assertEquals(zone, commit.getZoneId());
        assertEquals(3, commit.getDayOfWeek());

    }
}