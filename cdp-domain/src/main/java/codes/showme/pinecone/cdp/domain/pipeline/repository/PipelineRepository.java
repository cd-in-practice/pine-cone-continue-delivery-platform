package codes.showme.pinecone.cdp.domain.pipeline.repository;

import codes.showme.pinecone.cdp.domain.pipeline.Pipeline;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;

public interface PipelineRepository {

    Pagination<Pipeline> pagination(String appId, PageRequest pageRequest);
}
