package codes.showme.pinecone.cdp.web.api;

import codes.showme.pinecone.cdp.code.analysis.CodePlatformType;

public class CreateSyncTaskReq {
    private CodePlatformType codePlatformType;
    private String url;
    private String namespace;
    private String token;

    public CodePlatformType getCodePlatformType() {
        return codePlatformType;
    }

    public void setCodePlatformType(CodePlatformType codePlatformType) {
        this.codePlatformType = codePlatformType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
