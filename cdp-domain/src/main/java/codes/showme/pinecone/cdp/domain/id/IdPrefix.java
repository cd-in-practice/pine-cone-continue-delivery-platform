package codes.showme.pinecone.cdp.domain.id;

public enum IdPrefix {
    TICKET("tik"), TICKET_EVENT("tke");


    private String val;

    IdPrefix(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
