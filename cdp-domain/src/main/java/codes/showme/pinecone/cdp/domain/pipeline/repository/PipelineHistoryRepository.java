package codes.showme.pinecone.cdp.domain.pipeline.repository;

import codes.showme.pinecone.cdp.domain.pipeline.PipelineHistory;
import codes.showme.pinecone.cdp.techcommon.pagination.PageRequest;
import codes.showme.pinecone.cdp.techcommon.pagination.Pagination;

import java.io.Serializable;

public interface PipelineHistoryRepository {
    Serializable save(PipelineHistory pipelineHistory);

    Pagination<PipelineHistory> pagination(String appId, PageRequest pageRequest);
}
