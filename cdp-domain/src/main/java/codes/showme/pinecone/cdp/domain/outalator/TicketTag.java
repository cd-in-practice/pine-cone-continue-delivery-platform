package codes.showme.pinecone.cdp.domain.outalator;

import org.apache.commons.lang3.builder.CompareToBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "cdp_ticket_tags")
public class TicketTag implements Serializable{

    private static final long serialVersionUID = -3037243383111308197L;
    @Id
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "tag_key", length = 32)
    private String tagKey;
    @Column(name = "tag_value", length = 40)
    private String tagValue;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagKey() {
        return tagKey;
    }

    public void setTagKey(String tagKey) {
        this.tagKey = tagKey;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TicketTag ticketTag = (TicketTag) o;
        return Objects.equals(tagKey, ticketTag.tagKey) &&
                Objects.equals(tagValue, ticketTag.tagValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagKey, tagValue);
    }

}
