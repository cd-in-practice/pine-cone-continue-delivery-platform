package codes.showme.pinecone.cdp.techcommon;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface JsonService {
    <T> String toJsonString(T t);

    <T> T toObject(byte[] src, Class<T> tClass);

    HashMap<String, Object> toHashMap(String rawContent) throws IOException;
}
