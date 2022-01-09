package codes.showme.pinecone.cdp.alert.domain.prometheus;

import java.util.*;

public class HookMessage {
    private String version;
    private String groupKey;
    private String receiver;
    private Map<String, String> groupLabels = new HashMap<>();
    private Map<String, String> commonLabels = new HashMap<>();
    private Map<String, String> commonAnnotations = new HashMap<>();
    private String externalURL;
    private List<AlertStruct> alerts = new ArrayList<>();

    private String getAlertName() {
        return commonAnnotations.getOrDefault("alertname", "");
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Map<String, String> getGroupLabels() {
        return groupLabels;
    }

    public void setGroupLabels(Map<String, String> groupLabels) {
        this.groupLabels = groupLabels;
    }

    public Map<String, String> getCommonLabels() {
        return commonLabels;
    }

    public void setCommonLabels(Map<String, String> commonLabels) {
        this.commonLabels = commonLabels;
    }

    public Map<String, String> getCommonAnnotations() {
        return commonAnnotations;
    }

    public void setCommonAnnotations(Map<String, String> commonAnnotations) {
        this.commonAnnotations = commonAnnotations;
    }

    public String getExternalURL() {
        return externalURL;
    }

    public void setExternalURL(String externalURL) {
        this.externalURL = externalURL;
    }

    public List<AlertStruct> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertStruct> alerts) {
        this.alerts = alerts;
    }
}
