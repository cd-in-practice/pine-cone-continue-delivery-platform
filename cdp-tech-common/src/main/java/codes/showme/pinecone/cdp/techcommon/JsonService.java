package codes.showme.pinecone.cdp.techcommon;

public interface JsonService {
    <T> String toJsonString(T t);

    <T> T toObject(byte[] src, Class<T> tClass);
}
