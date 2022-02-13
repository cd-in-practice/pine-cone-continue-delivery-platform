package codes.showme.pinecone.cdp.domain.alert.repository;

import codes.showme.pinecone.cdp.domain.alert.PrometheusAlert;

import java.io.Serializable;

public interface PrometheusAlertRepository {
    Serializable save(PrometheusAlert prometheusAlert);
}
