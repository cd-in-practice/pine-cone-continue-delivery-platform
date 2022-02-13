package codes.showme.pinecone.cdp.techcommon;

import java.io.IOException;
import java.util.Map;

public interface JsonService {
    <T> String toJsonString(T t);

    <T> T toObject(byte[] src, Class<T> tClass);

    Map<String, Object> toHashMap(String rawContent) throws IOException;
}
