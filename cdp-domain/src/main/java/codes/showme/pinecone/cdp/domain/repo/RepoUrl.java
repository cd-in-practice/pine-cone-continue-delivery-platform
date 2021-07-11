package codes.showme.pinecone.cdp.domain.repo;

import javax.persistence.Embeddable;

@Embeddable
public class RepoUrl {


    private RepoType repoType;

    private String url;

}
