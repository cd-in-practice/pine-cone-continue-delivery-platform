package codes.showme.pinecone.cdp.domain.alert;

import codes.showme.pinecone.cdp.domain.DomainEntity;
import codes.showme.pinecone.cdp.domain.alert.repository.PrometheusAlertRepository;
import codes.showme.pinecone.cdp.techcommon.JsonService;
import codes.showme.pinecone.cdp.techcommon.idgenerator.IdGenerator;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import io.ebean.annotation.DbJson;
import io.ebean.annotation.DbJsonB;
import io.ebean.annotation.DbMap;
import io.ebean.text.json.EJson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static codes.showme.pinecone.cdp.domain.id.IdPrefix.PROMETHEUS_ALERT;

@Entity
@Table(name = "cds_prometheus_alerts")
public class PrometheusAlert implements Serializable, DomainEntity<PrometheusAlert> {

    private static final int COLUMN_NAMESPACE_SIZE = 32;

    @Id
    @Column(name = "id")
    private String id;

    @DbMap
    @Column(name = "raw_content", columnDefinition = "jsonb")
    private HashMap<String, Object> rawContent = new HashMap<>();

    @Column(name = "namespace", length = COLUMN_NAMESPACE_SIZE)
    private String namespace;

    public static PrometheusAlert buildNew() {
        PrometheusAlert result = new PrometheusAlert();
        IdGenerator idGenerator = InstanceFactory.getInstance(IdGenerator.class);
        result.setId(idGenerator.generateWithPrefix(result.getIdPrefix()));
        return result;
    }

    public void setRawContent(String rawContent) {
        try {
            this.rawContent = InstanceFactory.getInstance(JsonService.class).toHashMap(rawContent);
        } catch (IOException e) {
            this.rawContent = new HashMap<>();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getIdPrefix() {
        return PROMETHEUS_ALERT.getVal();
    }

    @Override
    public Serializable save() {
        PrometheusAlertRepository repository = InstanceFactory.getInstance(PrometheusAlertRepository.class);
        return repository.save(this);
    }

    public HashMap<String, Object> getRawContent() {
        return rawContent;
    }

    public void setRawContent(HashMap<String, Object> rawContent) {
        this.rawContent = rawContent;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
