package codes.showme.pinecone.cdp.domain;


import java.io.Serializable;

/**
 * all entity would be implemented DomainEntity
 */
public interface DomainEntity<T>{

    static final int ID_LENGTH = 32;

    /**
     * @return every id of entity should be have a prefix
     */
    String getIdPrefix();

    Serializable save();

}
