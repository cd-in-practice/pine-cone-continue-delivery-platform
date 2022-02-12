package codes.showme.pinecone.cdp.grpc;

import code.showme.pinecone.cdp.artifact.JavaArtifactGrpc;
import code.showme.pinecone.cdp.artifact.JavaArtifactOuterClass;
import codes.showme.pinecone.cdp.domain.artifact.Artifact;
import codes.showme.pinecone.cdp.domain.artifact.JavaArtifactCoordinate;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class JavaArtifactController extends JavaArtifactGrpc.JavaArtifactImplBase {
    @Override
    public void push(JavaArtifactOuterClass.JavaArtifactRequest request, StreamObserver<JavaArtifactOuterClass.JavaArtifactReply> responseObserver) {
        try {
            Artifact artifact = convert(request);
            artifact.save();
            responseObserver.onNext(JavaArtifactOuterClass.JavaArtifactReply.newBuilder().setReceived(true).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            // TODO specific error
            responseObserver.onError(e);
        }
    }

    private Artifact convert(JavaArtifactOuterClass.JavaArtifactRequest request) {
        Artifact result = new Artifact();
        result.setAppId(request.getAppId());
        result.setArtifactVersion(request.getArtifactVersion());
        result.setPipelineHistoryId(request.getPipelineHistoryId());
        result.setPipelineHistoryId(request.getPipelineHistoryId());
        result.setBuildNumber(request.getBuildNumber());
        result.setNamespace(request.getNamespace());

        JavaArtifactCoordinate javaArtifactCoordinate = new JavaArtifactCoordinate();
        javaArtifactCoordinate.setRepoId(request.getRepoId());
        javaArtifactCoordinate.setJavaArtifactVersion(request.getJavaArtifactVersion());
        javaArtifactCoordinate.setGroupId(request.getGroupId());
        javaArtifactCoordinate.setArtifactId(request.getArtifactId());
        result.setCoordinate(javaArtifactCoordinate);

        return result;
    }
}
