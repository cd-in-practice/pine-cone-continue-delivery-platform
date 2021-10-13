package codes.showme.pinecone.cdp.code.analysis.gitlab;

import codes.showme.pinecone.cdp.techcommon.JsonService;
import codes.showme.pinecone.cdp.techcommon.JsonServiceImpl;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceProvider;
import org.junit.Test;
import org.mockito.Mockito;

public class SyncGitLabCommitsEventTest {

    @Test
    public void toJsonString() {
        InstanceProvider instanceProvider = Mockito.mock(InstanceProvider.class);
        Mockito.when(instanceProvider.getInstance(JsonService.class)).thenReturn(new JsonServiceImpl());
        InstanceFactory.setInstanceProvider(instanceProvider);
        System.out.println(new SyncGitLabCommitsEvent(
                "default",
                "http://gitlab.org",
                "123",
                "token"
        ).toJsonString());
    }
}