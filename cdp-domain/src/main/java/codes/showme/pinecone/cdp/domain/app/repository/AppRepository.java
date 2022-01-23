package codes.showme.pinecone.cdp.domain.app.repository;

import codes.showme.pinecone.cdp.domain.app.App;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;

public interface AppRepository {
    Pagination<App> listByNamespace(String namespace, PageRequest pageRequest);

    void save(App app);
}
