package codes.showme.pinecone.cdp.techcommon.idgenerator;

public interface IdGenerator {
    String generate();
    String generateWithPrefix(String prefix);
}
