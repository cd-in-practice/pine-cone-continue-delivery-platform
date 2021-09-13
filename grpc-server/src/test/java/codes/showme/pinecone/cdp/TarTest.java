package codes.showme.pinecone.cdp;


import code.showme.pinecone.cdp.artifact.TarArtifactGrpc;
import code.showme.pinecone.cdp.artifact.TarArtifactOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.Ignore;

import java.util.logging.Level;

@Ignore
public class TarTest {

    @org.junit.Test
    @Ignore
    public void name() throws InterruptedException {
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress("127.0.0.1", 6565);
        ManagedChannel channel = channelBuilder.usePlaintext().build();
        TarArtifactGrpc.TarArtifactBlockingStub tarArtifactBlockingStub = TarArtifactGrpc.newBlockingStub(channel);
        TarArtifactOuterClass.TarArtifactReply reply = tarArtifactBlockingStub.push(TarArtifactOuterClass.TarArtifactRequest.newBuilder()
                .setAppId("appId")
                .setUrl("http://url")
                .setArtifactVersion("123123")
                .setBuildNumber(111)
                .setName("artifactName")
                .setNamespace("name")
                .setPipelineHistoryId("historyid")
                .setPipelineHistoryUrl("http://url")
                .build());
        System.out.println(reply.getReceived() + "--------------------");
        Thread.sleep(1000);
    }

}
