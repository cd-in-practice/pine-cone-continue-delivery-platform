package codes.showme.pinecone.cdp.code.analysis.service;

public interface SyncGitLabService {


    void sync(SyncGitLabRepoReq req, String namespace);

    class SyncGitLabRepoReq {
        private String gitLabServerUrl;
        private Object gitlabProjectObject;
        private String token;
        private String namespace;

        public String getGitLabServerUrl() {
            return gitLabServerUrl;
        }

        public void setGitLabServerUrl(String gitLabServerUrl) {
            this.gitLabServerUrl = gitLabServerUrl;
        }

        public Object getGitlabProjectObject() {
            return gitlabProjectObject;
        }

        public void setGitlabProjectObject(Object gitlabProjectObject) {
            this.gitlabProjectObject = gitlabProjectObject;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }
    }
}
