package codes.showme.pinecone.cdp.domain.app.repository;

import codes.showme.pinecone.cdp.domain.app.App;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;
import org.springframework.stereotype.Component;

@Component
public class AppMockRepository implements AppRepository{
    @Override
    public Pagination<App> listByNamespace(String namespace, PageRequest pageRequest) {

        return Pagination.empty();
    }
}
