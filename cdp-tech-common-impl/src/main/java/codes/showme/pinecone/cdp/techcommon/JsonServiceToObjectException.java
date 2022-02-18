package codes.showme.pinecone.cdp.techcommon;

import java.io.IOException;

public class JsonServiceToObjectException extends RuntimeException {
    public JsonServiceToObjectException(IOException e) {
        super(e);
    }
}
