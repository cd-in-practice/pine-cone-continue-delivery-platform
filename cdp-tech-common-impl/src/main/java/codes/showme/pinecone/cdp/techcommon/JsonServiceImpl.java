package codes.showme.pinecone.cdp.techcommon;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonServiceImpl implements JsonService {
    private static final Logger log = LoggerFactory.getLogger(JsonServiceImpl.class);
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> String toJsonString(T t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (Exception e) {
            log.error("json service to json string error,obj:{}", t, e);
            throw new JsonServiceToJsonStringException();
        }
    }

    @Override
    public <T> T toObject(byte[] src, Class<T> tClass) {
        try {
            return objectMapper.readValue(src, tClass);
        } catch (IOException e) {
            log.error("json service to object error,src:{}", src, e);
            throw new JsonServiceToObjectException(e);
        }
    }

    @Override
    public Map<String, Object> toHashMap(String rawContent) {
        try {
            TypeReference<HashMap<String, Object>> typeRef
                    = new TypeReference<HashMap<String, Object>>() {
            };
            return objectMapper.readValue(rawContent, typeRef);
        } catch (IOException e) {
            log.error("json service to map error,src:{}", rawContent, e);
            throw new JsonServiceToObjectException(e);
        }
    }
}
