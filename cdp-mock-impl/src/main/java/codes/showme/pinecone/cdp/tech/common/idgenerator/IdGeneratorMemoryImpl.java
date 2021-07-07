package codes.showme.pinecone.cdp.tech.common.idgenerator;


import codes.showme.pinecone.cdp.techcommon.idgenerator.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdGeneratorMemoryImpl implements IdGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
